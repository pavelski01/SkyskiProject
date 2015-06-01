SkyskiProject
=============

Description: Online store
Needs: Java EE 6 certified application server, SQL database management system
Tested on: GlassFish Server Open Source Edition 4.1, PostgreSQL 9.3
To do: 
1. Create new database "skyski".
2. Execute scripts in folder:
  * sql > ddl > sequences
  * sql > ddl > tables
  * sql > dml
3. Configure "PostgresqlPool" JDBC connection pool.
4. Configure "jdbc/postgresql" JDBC resource.
5. Configure "mail/skyskiGmail" JavaMail session.
6. Add user (User ID: user, Group List: appuser) to default-config > Security > Realms > file.

Technical note: WEB module contains EJB classes.
