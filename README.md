# Shop API

## Overview
A simple RESTful API built with Spring Boot for practicing backend development with Java.
This project provides product and category management features, including CRUD operations, category filtering, validation, exception handling, and unit tests.

## Tech Stack
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JUnit 5
- Mockito
- MockMvc
- Postman

## Features

### Product
- Get all products
- Get product by ID
- Create product
- Update product
- Delete product
- Filter products by category

### Category
- Get all categories
- Create category

### Other
- Request validation
- Global exception handling
- DTO-based request and response models
- JPA relationship mapping
- Unit tests for Service and Controller layers

## API Endpoints
| Method | Endpoint                  | Description                 |
|--------|---------------------------|-----------------------------|
| GET    | /products                 | Get all products            |
| GET    | /products/{id}            | Get product by ID           |
| GET    | /products?categoryId={id} | Filter products by category |
| POST   | /products                 | Create a product            |
| PATCH  | /products/{id}            | Update a product            |
| DELETE | /products/{id}            | Delete a product            |
| GET    | /categories               | Get all categories          |
| POST   | /categories               | Create a category           |

## Example

### Create Product

Request:

```json
{
"name": "Trackball Mouse",
"price": 3850,
"cost": 3200,
"stock": 16,
"categoryId": 2
}
```
Response:

```json
{
"id": 21,
"name": "Trackball Mouse",
"price": 3850,
"stock": 16,
"categoryId": 2,
"categoryName": "Mouse"
}
```

## Testing
Unit tests cover the Service and Controller layers.
Service tests use Mockito to mock repository dependencies.
Controller tests use MockMvc to verify:

- HTTP status codes
- JSON responses
- Request validation
- Exception handling

## How to Run
Requirements: Java 17, MySQL, Maven
1. Create a MySQL database named shop.
2. Configure the database connection.
3. Set the DB_USERNAME and the DB_PASSWORD environment variable.
   DB_USERNAME=your_mysql_username
   DB_PASSWORD=your_mysql_password
4. Run the Spring Boot application.
5. Test the API with Postman or another HTTP client.