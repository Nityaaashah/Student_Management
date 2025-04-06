# Student Management System

A Spring Boot application that manages student records with the help of MySQL and exposes REST APIs documented with Swagger UI. This project follows modern best practices including Spring Boot 3.4.4, JPA for ORM.


## Features

- Add, update, delete, and view student details.
- Spring Security debug enabled for development visibility.
-  MySQL integration using Spring Data JPA.
- Swagger-based interactive API documentation.
- Auto table creation/updates with Hibernate.

##  Technologies Used

- Java 17+
- Spring Boot 3.4.4
- Spring Data JPA
- MySQL
- Hibernate (JPA)
- Spring Security (debug only)
- Springdoc OpenAPI (Swagger UI)
- Maven

##  Prerequisites

Make sure the following are installed:

- Java 17+
- Maven 3.6+
- MySQL Server

##  Configuration (`application.properties`)

# App Identity
spring.application.name=Student_Management

# MySQL DB Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate JPA Config
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Spring Security (for debugging)
logging.level.org.springframework.security=DEBUG

# Swagger UI Config
springdoc.api-docs.enabled=true
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
