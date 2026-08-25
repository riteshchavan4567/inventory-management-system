# Inventory Management System

A backend REST API built using Spring Boot for managing inventory products.

## Features

- Create a product
- Get all products
- Get product by ID
- Update product
- Delete product
- Search products by name
- Search products by category
- Pagination
- Sorting
- Input validation
- Global exception handling
- Swagger/OpenAPI documentation
- Unit testing
- Integration testing
- MySQL database integration

## Technologies Used

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- MySQL
- Maven
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Git & GitHub

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.ritesh.inventory
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── exception
│   │       ├── repository
│   │       └── service
│   └── resources
│       └── application.properties
│
└── test
    └── java
        └── com.ritesh.inventory
            └── ProductControllerIntegrationTest.java