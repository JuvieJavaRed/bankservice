# bankservice
This is a spring boot project developed in Java 8. 
The security features have been commented out but if needed the endpoints can be secure by OAuth2...which is use of a jwt endpoint
To access the endpoints use http://localhost:8080/swagger-ui.html
The database that was used in this case was a postgres database however it can be changed by switching the driver in the pom.xml file to your database of choice
Once you have changed the database when you first run your project change spring.jpa.hibernate.ddl-auto=update to spring.jpa.hibernate.ddl-auto=create to generate database tables
At that point you should be ready to go.
