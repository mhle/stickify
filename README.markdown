# Introduction

Stickify allows you to remember and share small content easily. Think of it as virtual Post-its that can be shared between groups of friends.

# Requirements
- Servlet 3.0 container, e.g. Tomcat 7 or Jetty 8
- Maven 3
- MySQL server

# Developer notes

In order to run this application locally:

1. Execute the stickify.sql script located under /src/main/resources/database OR open stickify.mwb (located in the same directory as the sql file) in MySQL Workbench and perform a forward engineer of the model.
2. The above script should create the following user with full access to the newly created schema: username: stickify, password: st1ck1fy
3. Deploy to an embedded Tomcat 7 instance by issuing the following command: mvn tomcat7:run  
4. Point your browser to http://localhost:8080/stickify

In order to login as admin user user the following credentials:
username: stickify, password: stickify