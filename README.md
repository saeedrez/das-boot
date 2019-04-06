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

