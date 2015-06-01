SkyskiProject
=============

Description: Online store

Needs: Java EE 6 certified application server, SQL database management system

Tested on: GlassFish Server Open Source Edition 4.1, PostgreSQL 9.3

To do:
Create new database "skyski".
Execute scripts in folder:
sql > ddl > sequences
sql > ddl > tables
sql > dml
Configure "PostgresqlPool" JDBC connection pool.
Configure "jdbc/postgresql" JDBC resource.
Configure "mail/skyskiGmail" JavaMail session.
Add user (User ID: user, Group List: appuser) to default-config > Security > Realms > file.

Technical note: WEB module contains EJB classes.
