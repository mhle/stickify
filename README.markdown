# Introduction

Stickify allows you to remember and share small content easily. Think of it as virtual Post-its that can be shared between groups of friends.

# Requirements
- Servlet 3.0 container, e.g. Tomcat 7 or Jetty 8 (if you don't want to deploy to the embedded server)
- Maven 3
- MySQL 5

# Developer notes

In order to run this application locally:

1. Execute the stickify.sql script located under /src/main/resources/database 
e.g. mysql -u root -p < stickify.sql

OR open stickify.mwb (located in the same directory as the sql file) in MySQL Workbench and perform a forward engineer of the model.

2. The above sql script will create the following user with full access to the newly created schema: username: stickify, password: st1ck1fy

3. Issue the command mvn tomcat7:run in order to deploy to an embedded Tomcat 7 instance.  

4. Point your browser to http://localhost:8080/stickify

In order to login as admin user user the following credentials:
username: stickify, password: stickify