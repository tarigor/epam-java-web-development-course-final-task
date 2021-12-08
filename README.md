## Java Web Development course from EPAM - final task

### Task description:

System **Hotel booking**. 
- **The client** fills in the **Application**, indicating the number of beds in the room, the class of apartments
and the time of stay. 
- **The administrator** reviews the received **Application**, selects the most suitable available
**Room(s)**, 
- after which the system exposes **Invoice to the Client**.

### Requirements:

1. Store information about the subject area in the database (MySQL is recommended).
2. Based on the entities of the subject area, create classes describing them.
3. Implement the functionality proposed in the formulation of a specific task.
4. Classes and methods should have names reflecting their functionality and should be competently structured by packages.
5. When developing, use event logging (Log4j or similar).
6. The code must contain comments.
7. Use unit tests.
8. Use the build libraries.

### Resources

- factory/command.json - contains items of the commands factory
- factory/dao.json - contains items of the DAO factory
- factory/menu.json - contains items of the menu factory
- factory/services - contains items of the services factory
- local/local_by_BY.properties - contains the localization texts to English language
- local/local_en_US.properties - contains the localization texts to Belarusian language
- sql/hotel_db.sql - contains SQL statements to create the tables and stored procedures of the database for the current project.
- database.properties - contains the settings to initialize the MySQL database uses in the current project
- log4j.properties - contains the settings to initialize the logger - Log4j 
        
#### Technologies:

- Java (JDK 11)
- maven (build tool)
- apache tomcat 9.0.45
- Servlet API 3.1.0
- JSP API 2.1
- JSTL API 1.2
- commons-codec 1.11
- log4j (logger) 1.2.17
- junit (testing) 4.13.2
- junit.jupiter 5.8.1
- mariaDB4j 2.5.0 (DAO layer testing)
- slf4j 1.7.32 (logging of mariaDB4j)

#### Database schema:

![hoteDB](hotelDB.png)

#### Site Link:

http://hotelgrodnoinn-env.eba-vzzi3eub.us-east-2.elasticbeanstalk.com