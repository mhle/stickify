# Introduction

Stickify allows you to remember and share small content easily. Think of it as virtual Post-its that can be shared between groups of friends.

# Developer notes

In order to run this distribution locally, make sure that you have a local version of the database installed. 
You can either create this manually (not recommended) or simply import it using MySQL Workbench and the included model found under /database/stickify.mwb.
To run the application it is assumed that the following user has full access to the database: 

username: stickify, password: st1ck1fy

The above model will for conveniency insert the following ADMIN user into the user table: 

username: stickify, password: stickify