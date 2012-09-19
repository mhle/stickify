# Introduction

Stickify allows you to remember and share small content easily. Think of it as virtual Post-its that can be shared between groups of friends.

# Requirements
- Servlet 3.0 container, e.g. Tomcat 7 or Jetty 8
- Maven 3

# Developer notes

In order to run this distribution locally:

1. Execute the stickify.sql script located under /src/main/resources/database OR open stickify.mwb (located in the same directory as the sql file) in MySQL Workbench and perform a forward engineer of the model.
2. Make sure the following user has full access to the newly created schema:
username: stickify, password: st1ck1fy
3. Deploy with the following maven command: 
4. Point your browser to http://localhost:8080/stickify
5. Login as admin:
username: stickify, password: stickify