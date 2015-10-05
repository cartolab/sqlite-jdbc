/*
 * Copyright (c) 2007 David Crawshaw <david@zentus.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package org.sqlite.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Implements a JDBC ResultSet.
 */
public abstract class CoreResultSet implements Codes
{
    protected final CoreStatement stmt;
    protected final DB   db;
    protected String keyName = "gid";

    public boolean            open     = false; // true means have results and can iterate them
    public int                maxRows;         // max. number of rows as set by a Statement
    public String[]           cols     = null; // if null, the RS is closed()
    public String[]           colsMeta = null; // same as cols, but used by Meta interface
    protected boolean[][]        meta     = null;
    protected String[] rowBuffer = null;
    protected Map<String, Object> updateValues = null;

    protected int        limitRows;       // 0 means no limit, must check against maxRows
    protected int        row      = 0;    // number of current row, starts at 1 (0 is for before loading data)
    protected int        lastCol;         // last column accessed, for wasNull(). -1 if none
    protected boolean onInsertRow = false; // are we on the insert row (for
	   // JDBC2 updatable resultsets)
    protected PreparedStatement insertStatement = null;
    protected PreparedStatement updateStatement = null;
    protected PreparedStatement deleteStatement = null;
    protected boolean doingUpdates = false;

    public boolean closeStmt;
    protected Map<String, Integer> columnNameToIndex = null;

    /**
     * Default constructor for a given statement.
     * @param stmt The statement.
     * @param closeStmt TODO
     */
    protected CoreResultSet(CoreStatement stmt) {
        this.stmt = stmt;
        this.db = stmt.db;
    }

    // INTERNAL FUNCTIONS ///////////////////////////////////////////

    protected void checkColumnIndex(int column) throws SQLException {
	if (column < 1 || column > cols.length)
	    throw new SQLException("The column index is out of range: "
		    + column + ", number of columns: " + cols.length + ".");
    }

    public String getString(int col) throws SQLException {
	return db.column_text(stmt.pointer, markCol(col));
    }

    public int findColumn(String col) throws SQLException {
	checkOpen();
	int c = -1;
	for (int i = 0; i < cols.length; i++) {
	    if (col.equalsIgnoreCase(cols[i])
		    || (cols[i].toUpperCase().endsWith(col.toUpperCase()) && cols[i]
			    .charAt(cols[i].length() - col.length()) == '.')) {
		if (c == -1) {
		    c = i;
		} else {
		    throw new SQLException("ambiguous column: '" + col + "'");
		}
	    }
	}
	if (c == -1) {
	    throw new SQLException("no such column: '" + col + "'");
	} else {
	    return c + 1;
	}
    }

    protected void clearRowBuffer(boolean copyCurrentRow) throws SQLException {
	// rowBuffer is the temporary storage for the row
	rowBuffer = new String[cols.length];

	// inserts want an empty array while updates want a copy of the current
	// row
	if (copyCurrentRow) {
	    for (int i = rowBuffer.length - 1; i >= 0; i--) {
		rowBuffer[i] = getString(i);
	    }
	}

	// clear the updateValues hashTable for the next set of updates
	updateValues.clear();

    }

    protected void updateRowBuffer() throws SQLException {

	Iterator<String> columns = updateValues.keySet().iterator();

	while (columns.hasNext()) {
	    String columnName = (String) columns.next();
	    int columnIndex = findColumn(columnName) - 1;

	    Object valueObject = updateValues.get(columnName);
	    if (valueObject == null) {
		rowBuffer[columnIndex] = null;
	    } else {
		/*
		 * THE CONNECTION DOES NOT PROVIDE ALL THE NEEDED METHODS FOR
		 * THE PROPER VALUES DISPLAY WE JUST SET IT AS THE STRINGVALUE
		 */
		rowBuffer[columnIndex] = String.valueOf(valueObject);
		// switch (getColumnType(columnIndex + 1)) {
		//
		// case Types.DECIMAL:
		// case Types.BIGINT:
		// case Types.DOUBLE:
		// case Types.BIT:
		// case Types.VARCHAR:
		// case Types.SMALLINT:
		// case Types.FLOAT:
		// case Types.INTEGER:
		// case Types.CHAR:
		// case Types.NUMERIC:
		// case Types.REAL:
		// case Types.TINYINT:
		// case Types.ARRAY:
		// case Types.OTHER:
		// rowBuffer[columnIndex] = connection.encodeString(String
		// .valueOf(valueObject));
		// break;
		//
		// //
		// // toString() isn't enough for date and time types; we must
		// // format it correctly
		// // or we won't be able to re-parse it.
		// //
		//
		// case Types.DATE:
		// rowBuffer[columnIndex] = connection.encodeString(connection
		// .getTimestampUtils().toString(null,
		// (Date) valueObject));
		// break;
		//
		// case Types.TIME:
		// rowBuffer[columnIndex] = connection.encodeString(connection
		// .getTimestampUtils().toString(null,
		// (Time) valueObject));
		// break;
		//
		// case Types.TIMESTAMP:
		// rowBuffer[columnIndex] = connection.encodeString(connection
		// .getTimestampUtils().toString(null,
		// (Timestamp) valueObject));
		// break;
		//
		// case Types.NULL:
		// // Should never happen?
		// break;
		//
		// case Types.BINARY:
		// case Types.LONGVARBINARY:
		// case Types.VARBINARY:
		// if (fields[columnIndex].getFormat() == Field.BINARY_FORMAT) {
		// rowBuffer[columnIndex] = (byte[]) valueObject;
		// } else {
		// try {
		// rowBuffer[columnIndex] = PGbytea.toPGString(
		// (byte[]) valueObject)
		// .getBytes("ISO-8859-1");
		// } catch (UnsupportedEncodingException e) {
		// throw new PSQLException(
		// GT.tr("The JVM claims not to support the encoding: {0}",
		// "ISO-8859-1"),
		// PSQLState.UNEXPECTED_ERROR, e);
		// }
		// }
		// break;
		//
		// default:
		// rowBuffer[columnIndex] = (byte[]) valueObject;
		// }

	    }
	}
    }

    protected void checkInsertable() throws SQLException {
	if ((cols == null) || (cols.length == 0)) {
	    throw new SQLException("This ResultSet has no metadata.");
	}
	if (updateValues == null) {
	    // allow every column to be updated without a rehash.
	    updateValues = new HashMap<String, Object>();
	}

    }

    protected void checkUpdateable() throws SQLException {
	if (!open) {
	    throw new SQLException("This ResultSet is closed.");
	}
	if (updateValues == null) {
	    // allow every column to be updated without a rehash.
	    updateValues = new HashMap<String, Object>();
	}

    }

    protected String getTableName(String sql) {
	String tableName = null, aux;
	int index = sql.toLowerCase().indexOf(" from ");
	if (index > -1) {
	    aux = sql.substring(index + 6).trim();
	    index = aux.indexOf(" ");
	    if (index == -1) {
		index = aux.indexOf(";");
		if (index == -1) {
		    index = aux.length();
		}
	    }
	    tableName = aux.substring(0, index);
	}
	return tableName;
    }

    /**
     * Checks the status of the result set.
     * @return True if has results and can iterate them; false otherwise.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @throws SQLException if ResultSet is not open.
     */
    protected void checkOpen() throws SQLException {
        if (!open) {
            throw new SQLException("ResultSet closed");
        }
    }

    /**
     * Takes col in [1,x] form, returns in [0,x-1] form
     * @param col
     * @return
     * @throws SQLException
     */
    public int checkCol(int col) throws SQLException {
        if (colsMeta == null) {
            throw new IllegalStateException("SQLite JDBC: inconsistent internal state");
        }
        if (col < 1 || col > colsMeta.length) {
            throw new SQLException("column " + col + " out of bounds [1," + colsMeta.length + "]");
        }
        return --col;
    }

    /**
     * Takes col in [1,x] form, marks it as last accessed and returns [0,x-1]
     * @param col
     * @return
     * @throws SQLException
     */
    protected int markCol(int col) throws SQLException {
        checkOpen();
        checkCol(col);
        lastCol = col;
        return --col;
    }

    /**
     * @throws SQLException
     */
    public void checkMeta() throws SQLException {
        checkCol(1);
        if (meta == null) {
            meta = db.column_metadata(stmt.pointer);
        }
    }

    public void close() throws SQLException {
        cols = null;
        colsMeta = null;
        meta = null;
        open = false;
        limitRows = 0;
        row = 0;
        lastCol = -1;
        columnNameToIndex = null;

        if (stmt == null) {
            return;
        }

        if (stmt != null && stmt.pointer != 0) {
            db.reset(stmt.pointer);

            if (closeStmt) {
                closeStmt = false; // break recursive call
                ((Statement)stmt).close();
            }
        }
    }

    protected Integer findColumnIndexInCache(String col) {
        if (columnNameToIndex == null) {
            return null;
        }
        return columnNameToIndex.get(col);
    }

    protected int addColumnIndexInCache(String col, int index) {
        if (columnNameToIndex == null) {
            columnNameToIndex = new HashMap<String, Integer>(cols.length);
        }
        columnNameToIndex.put(col, index);
        return index;
    }
}
