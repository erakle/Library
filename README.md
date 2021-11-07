# LIBRARY API

This is a sample Java / Maven / Spring Boot Restful CRUD api for a simple author-book application.



**1. Create Mysql database**

```bash
create database bookdb
```

**2. Change mysql url, username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `url`, `user` and `password` as per your mysql installation

**3. Run the app**

```bash
java -jar library-0.0.1-SNAPSHOT.jar
```

The integrated Swagger will start running at <http://localhost:8080/swagger-ui.html>.
