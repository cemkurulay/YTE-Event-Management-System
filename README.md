# YTE-Event-Management-System
Summer internship project for learning Java Spring, React and PostgreSQL. Phase 1/5.

Possible problem solutions:  
  
-Change application database property about hibernate from "update" to "create"   
  
-You can add an admin user by changing UserService from 'new Authority(..., "PARTICIPANT")' to 'new Authority(..., "ADMIN")' and then /addUser PreAuthorize from "hasAuthority("ADMIN")" to "permitAll()". And you may revert the changes after the admin user is added.  
  
-Refreshing the page after clicking the sign in button.  
  
-Clicking refresh event table button if any event crud operation is not done.
