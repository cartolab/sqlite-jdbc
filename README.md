SQLite JDBC Driver tuned for gvSIG compliance
=============================================
This is a fork of the [original SQLite JDBC Driver](https://github.com/xerial/sqlite-jdbc) made by [Taro L. Saito](http://www.xerial.org/leo), which has been tuned in order to work as part of a [specific driver for gvSIG](https://github.com/cartolab/libSpatialite).
There have been three main changes:

* Added random access to ResultSet.
* Always keep an open transaction and ignore nested BEGINs, because gvSIG is very prone to execute nested COMMIT and BEGIN statements.
* Added CUD operations for alphanumeric data.

https://help.github.com/articles/fork-a-repo/


Check the [original README] (https://github.com/xerial/sqlite-jdbc), which still applies for almost the whole project, to get more info.


How to keep this fork up to upstream
====================================

After clone this repo add an upstream remote branch

```bash
git remote add upstream https://github.com/xerial/sqlite-jdbc.git
git fetch upstream
git checkout master
git merge upstream/master
```

Read also [this](https://help.github.com/articles/fork-a-repo/) and [this](https://help.github.com/articles/syncing-a-fork/) for more info.

Each time the repo is synced edit this file to include the most uptodate commit

https://github.com/xerial/sqlite-jdbc/commit/118288600bef15c4557cae901e73af3f14c80f7e

Release
=======

Each release of libSpatialite should be tagged with the same name/tag in this fork

Test
====

mvn test

Build
=====

export JAVA_HOME=PATH_TO_YOUR_JAVA_HOME_FOLDER
make

The output jar will be located at target/sqlite-jdbc-XXX.jar