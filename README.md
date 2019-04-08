# das-boot
Spring boot REST demo service with inmemory db - 2019-jan


To run:
from eclipse: rightclick on app.java and run as application

endpoints:
http://localhost:8080/index.html#/
http://localhost:8080/api/vi/shipwrecks (see controller files)

To add data to inmemory db:
either do it in the DataLoader.java which is preloaded before the application comes up .     or
go to index.html and click on add-shipwrecks and add a shipwreck.

To access the inmemory-db console:
go to: http://localhost:8080/h2 .  and then make sure jdbc-url points to: jdbc:h2:file:~/dasboot

Tech stuff:
Webapp and spring stuff:
Springboot contains a complete tomcat inside. It also supports other web servers like jetty.
To bootstrap springbok, all you need to do is add @SpringBootApplication to a class with main. That is it. It bootstraps springboot which in turns scans and reads all classes in the class path and looks e.g. for @Restcontrollers (in which case it deploys automatically to builtin tomcat) and jpsRepository (which cause spring to use spring data)

Development Steps for a rest-based web app using springboot:
Create pom with spring-starter... dependencies
Create a class with main with @SpringBootApplication annotation which will bootstrap springboot
Create a controller class with annotation @RestController and @RequestMapping (on class and its methods). Spring will know it is an endpoint and deploy it to the builtin tomcat. No external web app servers needed (you can use one if you want: deploy the way).
Now you have a functioning web app. But no db.
To config a db:
Create application.properties under resources with properties: spring.datasource.url... if it is a h2 in memory-db (3. Party but excellent spring support), use e.g. url= jdbc:h2:~/webapp1 (could be any thing after h2 part)
Create a class, PersisnceConfiguration (under config folder, which replaces the old xml context files) and do: DataSourceBuilder.create().build(), which will get the datasource ready behind the scenes to be used with jpaRepository. Flyway is also initiated here (which will look under db.migration to initial db table creation and inserts, all behind the scenes)
Create a blablaRepository extends JpaRepository(tableName, id) for each table being used.
Done. Now you have a functioning web with db support.

Test stuff:
Class ends with "blablaTest", but methods start with "testBlabla"
Using mocking: annotate with @Mock (this is the dependency to be mocked) and @InjectMock (the class mocks are inserted into)

Surefire failures:
It means "look at surefire reports" under target folder

DB stuff:
Overview of components: flyway (dependency, sql under db.migration, bootstrapping by springboot in a class with @Configuration annotation.), @entity on a model bean, spring data (jpaRepository). The table names in the sql must be the same as entity names (spring/flyway changes the entity names: e.g. TableObject to table_object).
H2databse and flyway are both 3. party libs with good builtin support from spring boot. Just include the dependencies in pom.
Concepts: table=entity in spring. For each table, you will have a repository in spring. E.g. Entity=TableObject connects to table_object in db using tableObjectRepository. So in db, you have table_object table. In java, you have an entity, TableObject (which spring turns into table_object when creating a sql to send down) and a tableObjectRepository(TableObject, id) which is used to interface to the db.
Flyway and spring: Spring supports flyway and on startup gives control to flyway (lib is in the class path) which looks under db/migration and checks and runs all sols.
What is flyway: it is a tool like git for tables. It is only run at startup to see if any tables are created and any sql are required to run to update the database. No need for db administrators.
Initializing a db can also be done programatically (without flyway) by creating a class that implements ApplicationRunner and implement run() method (use repository to create, insert...)
