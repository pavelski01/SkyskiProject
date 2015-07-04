SkyskiProject
=============

Description: Online store (bachelor)
Needs: Java EE 6 certified application server, SQL database management system  
Tested on: GlassFish Server Open Source Edition 4.1, PostgreSQL 9.3  
To do:  
[PostgreSQL]  
1.  Create new database "skyski".  
2.  Execute queries in folder:  
sql > ddl > sequences  
sql > ddl > tables  
sql > dml  
[GlassFish]  
3.  Configure "PostgresqlPool" JDBC connection pool.  
4.  Configure "jdbc/postgresql" JDBC resource.  
5.  Configure "mail/skyskiGmail" JavaMail session.  
6.  Add user (User ID: user, Group List: appuser) to default-config > Security > Realms > file.  
Technical note: EJB classes inside of WEB module; contains Apache Maven POM file, Eclipse IDE project files.  
