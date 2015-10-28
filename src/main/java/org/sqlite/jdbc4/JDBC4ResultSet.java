package org.sqlite.jdbc4;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.util.Map;

import org.sqlite.core.CoreStatement;
import org.sqlite.jdbc3.JDBC3ResultSet;

public class JDBC4ResultSet extends JDBC3ResultSet implements ResultSet, ResultSetMetaData {
    public JDBC4ResultSet(CoreStatement stmt) {
        super(stmt);
    }

    // JDBC 4
    public <T> T unwrap(Class<T> iface) throws ClassCastException {
        return iface.cast(this);
    }

    public boolean isWrapperFor(Class<?> iface) {
        return iface.isInstance(this);
    }

    public RowId getRowId(int columnIndex) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public int getHoldability() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isClosed() throws SQLException {
        return !isOpen();
    }

    public void updateNString(int columnIndex, String nString)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNString(String columnLabel, String nString)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(String columnLabel, NClob nClob)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public String getNString(int columnIndex) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public String getNString(String columnLabel) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader,
            long length) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(String columnLabel, InputStream x,
            long length) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(String columnLabel, Reader reader,
            long length) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(String columnLabel, InputStream inputStream,
            long length) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(int columnIndex, InputStream x)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(int columnIndex, InputStream x)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(String columnLabel, InputStream x)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(String columnLabel, InputStream x)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(int columnIndex, InputStream inputStream)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(String columnLabel, InputStream inputStream)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(String columnLabel, Reader reader)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(String columnLabel, Reader reader)
            throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        // TODO Support this
        throw new SQLFeatureNotSupportedException();
    }

    protected SQLException unused() {
        return new SQLException("not implemented by SQLite JDBC driver");
    }


    // ResultSet ////////////////////////////////////////////////////

    public Array getArray(int i)
        throws SQLException { throw unused(); }
    public Array getArray(String col)
        throws SQLException { throw unused(); }
    public InputStream getAsciiStream(int col)
        throws SQLException { throw unused(); }
    public InputStream getAsciiStream(String col)
        throws SQLException { throw unused(); }
//    public BigDecimal getBigDecimal(int col)
//        throws SQLException { throw unused(); }
    public BigDecimal getBigDecimal(int col, int s)
        throws SQLException { throw unused(); }
//    public BigDecimal getBigDecimal(String col)
//        throws SQLException { throw unused(); }
    public BigDecimal getBigDecimal(String col, int s)
        throws SQLException { throw unused(); }
    public Blob getBlob(int col)
        throws SQLException { throw unused(); }
    public Blob getBlob(String col)
        throws SQLException { throw unused(); }
    public Clob getClob(int col)
        throws SQLException { throw unused(); }
    public Clob getClob(String col)
        throws SQLException { throw unused(); }
    @SuppressWarnings("rawtypes")
    public Object getObject(int col, Map map)
        throws SQLException { throw unused(); }
    @SuppressWarnings("rawtypes")
    public Object getObject(String col, Map map)
        throws SQLException { throw unused(); }
    public Ref getRef(int i)
        throws SQLException { throw unused(); }
    public Ref getRef(String col)
        throws SQLException { throw unused(); }

    public InputStream getUnicodeStream(int col)
        throws SQLException { throw unused(); }
    public InputStream getUnicodeStream(String col)
        throws SQLException { throw unused(); }
    public URL getURL(int col)
        throws SQLException { throw unused(); }
    public URL getURL(String col)
        throws SQLException { throw unused(); }

    public void moveToCurrentRow() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }
    public boolean last() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }
    public boolean previous() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }
    public boolean relative(int rows) throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }
    public void afterLast() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }
    public void beforeFirst() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }
    public boolean first() throws SQLException {
        throw new SQLException("ResultSet is TYPE_FORWARD_ONLY"); }

    public void cancelRowUpdates()
        throws SQLException { throw unused(); }

    public void updateArray(int col, Array x)
        throws SQLException { throw unused(); }
    public void updateArray(String col, Array x)
        throws SQLException { throw unused(); }
    public void updateBlob(int col, Blob x)
        throws SQLException { throw unused(); }
    public void updateBlob(String col, Blob x)
        throws SQLException { throw unused(); }
    public void updateClob(int col, Clob x)
        throws SQLException { throw unused(); }
    public void updateClob(String col, Clob x)
        throws SQLException { throw unused(); }
    public void updateRef(int col, Ref x)
        throws SQLException { throw unused(); }
    public void updateRef(String c, Ref x)
        throws SQLException { throw unused(); }

    public void refreshRow()
        throws SQLException { throw unused(); }
}
