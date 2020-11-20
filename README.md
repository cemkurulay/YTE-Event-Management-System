# YTE-Event-Management-System
This is a summer internship project that I designed and developed from scratch.
Used Java Spring, React, Hibernate, PostgreSQL, Postman, and IntelliJ while developing.

The system validates input, authenticates users and provides authorization service by utilizing roles for every user.

Possible solutions if the application does not work properly:  
  
-Change application database property about hibernate from "update" to "create"   
  
-You can add an admin user by changing UserService from 'new Authority(..., "PARTICIPANT")' to 'new Authority(..., "ADMIN")' and then /addUser PreAuthorize from "hasAuthority("ADMIN")" to "permitAll()". And you may revert the changes after the admin user is added.  
  
-Refreshing the page after clicking the sign in button.  
  
-Clicking refresh event table button if any event crud operation is not done.
