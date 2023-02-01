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
NOTE: Exact path maybe different for you :0)

1) Start the database:
-Create the following directory structure ...\database\data
-Put hsqldb.jar in the database folder
-The data folder is where the database and tables and data will be generated
->cd C:\Users\<user>\ideaProjects\homeLandHistories\database (location of the hsqldb.jar)
->java -cp hsqldb.jar org.hsqldb.Server -database.0 file:./data/homelandDatabase -dbname.0 hldb

2) Open database UI and run scripts to add data:
->cd C:\Users\<user>\ideaProjects\homeLandHistories\database (location of the hsqldb.jar)
->java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
-Connection details:
-- Type: HSQL Database Engine In-Memory
-- Driver: org.hsqldb.jdbc.JDBCDriver
-- URL: jdbc:hsqldb:hsql://localhost:9001/hldb
-- User: SA
-- Password: No password
-NOTE: Check database for tables and data first. Likely do not need to set up tables and data.
->Run the included script file in three seperate steps: 1-tableCreationScript.sql to set up the person and address, 2-IndexAndConstraintScript to set up indexing and a constraint, 3-dataScript to insert some data into both tables
--Scripts located in project base folder ...\homeLandHistories\database\scripts

3) Create a .jar (skip this step if jar is already provided in the target folder):
-Make sure the database is running
->cd C:\Users\<user>\ideaProjects\homeLandHistories\homeland (top folder of project where pom.xml is)
->mvn clean install (runs maven to run unit test, create .jar)
-jar is placed in the target directory: C:\Users\<user>\ideaProjects\homeLandHistories\homeland\target

Note: I checked a homeland project executible jar into the following location: homeLandHistories\Project Jar

4) Example of running the .jar file from the target folder:
->cd C:\Users\<user>\ideaProjects\homeLandHistories\homeland\target
->java -jar target/homeland-0.0.1-SNAPSHOT.jar 

Note: I checked a homeland project executible jar into the following location: homeLandHistories\Project Jar

5) Run RESTful API's from Postman

-POST: 
--http://localhost:8080/api/add/person
--Example Body
{
    "firstName": "Sean",
    "lastName": "Smith",
    "address": {
        "street": "197 North 45 East",
        "city": "Sandy",
        "state": "Utah",
        "zipCode": "84044",
        "country": "United States of America"
    }
}

-GET:
--Find Person by first name: http://localhost:8080/api/person/firstName{?firstName}
---Example: http://localhost:8080/api/person/firstName?firstName=Fredrick

--Find Person by last name: http://localhost:8080/api/person/lastName{?lastName}
---Example:http://localhost:8080/api/person/lastName?lastName=Forest

--Find Person by first and last name: http://localhost:8080/api/person/fullName{?firstName,lastName}
---Example: http://localhost:8080/api/person/fullName?firstName=Jane&lastName=Doe
---Note: Use the response from this request to call the PUT method

--A Person: http://localhost:8080/api/persons/{id)
---Example: http://localhost:8080/api/persons/5
---Note: The query param is the primary key of the person.
---Note: The response is a person with the request URL to the associated address. This is set up automatically when using Spring Data JPA and Rest Repositories.

--All Persons w/Pagination:http://localhost:8080/api/persons{?page,size,sort}
---Example: http://localhost:8080/api/persons?page=0&size=5
---Note: The response is a list of persons with the request URLs to their address. This is set up automatically when using Spring Data JPA and Rest Repositories.

--An Address: http://localhost:8080/api/addresses/{id}
---Example: http://localhost:8080/api/addresses/5
---Note: The query param is the primary key of the address.
---Note: The response is an address with the request URL to the address. This is set up automatically when using Spring Data JPA and Rest Repositories.

--Address Pagination: http://localhost:8080/api/addresses{?page,size,sort}  
---Example: http://localhost:8080/api/addresses?page=0&size=5
---Note: The response is a list of addresses with the request URLs to the address. This is set up automatically when using Spring Data JPA and Rest Repositories.

-PUT: http://localhost:8080/api/update/person/{id}
---Example: http://localhost:8080/api/update/person/11
---Note: Use the GET method which finds a person using first and last name to get the id for this methods query param. The id is the primary key for the person object and forign key for the address object.

---Example Body (non of the fields in the body are required)
{
    "id": 3,
    "firstName": "Jane",
    "lastName": "Doe",
    "address": {
        "id": 13,
        "street": "100 North 5645 East",
        "city": "St. George",
        "state": "Utah",
        "zipCode": "84765",
        "country": "United States of America"
    }
}

-DELETE: http://localhost:8080/api/delete/person{?firstName,lastName}
---Example: http://localhost:8080/api/delete/person?firstName=Sean&lastName=Downs

