# homeLandHistories
 Homeland Histories Interview Project

Spring Initializer Dependencies/Projects 
-Lombok: Java annotation library which helps to reduce boilerplate code (specifically with the entities/POJOs in this case)
-Rest Repositories: Exposing Spring Data repositories over REST via Spring Data Rest
-Spring Data JPA: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
-HyperSQL Database: Lightweight 100% Java SQL Database Engine.

Additional Stack Info
-Java 19.0.2
-Apache Maven 3.8.7
-Spring Boot 3.0.2

IDE: IntelliJ

Steps to run the project...

1) Start the database:
-Create the following directory structure ...\database\data
-Put hsqldb.jar in the database folder
-The data folder is where the database and tables and data will be generated
->cd C:\Users\<user>\ideaProjects\homeLandHistories\database (location of the hsqldb.jar)
->java -cp hsqldb.jar org.hsqldb.Server -database.0 file:./data/homelandDatabase -dbname.0 hldb

2) Open database UI and run scripts to add data:
->cd C:\Users\<user>\ideaProjects\my-spring-boot-library\database (location of the hsqldb.jar)
->java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
-Connection details:
-- Type: HSQL Database Engine In-Memory
-- Driver: org.hsqldb.jdbc.JDBCDriver
-- URL: jdbc:hsqldb:hsql://localhost:9001/hldb
-- User: SA
-- Password: No password
->Run the included script file in two seperate steps: 1) run the tableCreationScript.sql to set up the person and address tables 2) run the dataScript.sql to insert some data into both tables
--Scripts located in project base folder ...\homeLandHistories\database\scripts

3) Create a .jar (skip this step if jar is already provided in the target folder):
-Make sure the database is running
->cd C:\Users\<user>\ideaProjects\homeLandHistories\homeland (top folder of project where pom.xml is)
->mvn clean install (runs maven to run unit test, create .jar)
-jar is placed in the target directory: C:\Users\<user>\ideaProjects\my-spring-boot-library\mySpringBootLibraryApp\target\mySpringBootLibraryApp-0.0.1-SNAPSHOT.jar

4) Run the .jar file:
->cd C:\Users\<user>\ideaProjects\homeLandHistories\homeland
->java -jar target/homeland-0.0.1-SNAPSHOT.jar 

5) Run RESTful API's from Postman
- C POST: 
--http://localhost:8080/api/add/person
---Not done yet. This works for the person, but the address is not yet done. An address is stubbed in the code which is saved to db. Need to finish by getting address from JSON address object. 
JSON
{
    "firstName": "Mark",
    "lastName": "Frank"
}


- R GET:
--Find Person by first Name: http://localhost:8080/api/person/firstName?firstName=Fredrick
--Find Person by last Name:http://localhost:8080/api/person/lastName?lastName=Forest

--All Persons: http://localhost:8080/api/persons
--A Person: http://localhost:8080/api/persons/{id)
--Person Pagination:http://localhost:8080/api/persons{?page,size,sort}
---Example: http://localhost:8080/api/persons?page=0&size=5

--All Address: http://localhost:8080/api/addresses
--An Address: http://localhost:8080/api/addresses/{id}
--Address Pagination: http://localhost:8080/api/addresses{?page,size,sort}  
---Example: http://localhost:8080/api/addresses?page=0&size=5

- U PUT:
--Not started yet

- D DELETE: 
--Not started yet
