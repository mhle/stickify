# Introduction

Stickify allows you to remember and share small content easily. Think of it as virtual post its that can be shared between groups of friends.

# Developer notes

In order to run this distribution locally, make sure that you have a local version of the database installed. 
You can either create this manually (not recommended) or simply import it using MySQL Workbench and the included model found at /database/stickify.mwb.
To run the application it is assumed that a database user with the credentials: 

username: stickify
password: st1ck1fy

has full access rights to the database. 

If the above model is used, the following user with admin rights is also inserted into the records: 

username: stickify
password: stickify