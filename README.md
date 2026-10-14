# E-Commerce Backend API

A backend REST API for an e-commerce application built using Spring Boot, Spring Data JPA, Hibernate, MySQL, Spring Security and JWT.

## Features

- User registration and login
- BCrypt password hashing
- JWT-based authentication
- Category management
- Product management
- Product search
- Pagination and sorting
- Input validation using Jakarta Validation
- Global exception handling
- Cart management
- Order placement
- Stock validation and stock reduction
- Transaction management using `@Transactional`
- Swagger/OpenAPI documentation

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- MySQL
- Maven
- Swagger/OpenAPI
- Postman
- IntelliJ IDEA

## Project Structure

```text
com.example.ecommerce
├── controller
├── dto
├── entity
├── repository
├── service
├── config
├── security
├── util
└── exception
```

## API Modules

### Authentication

```text
POST /api/auth/register
POST /api/auth/login
```

### Categories

```text
POST /api/categories
GET /api/categories
GET /api/categories/{id}
```

### Products

```text
POST /api/products
GET /api/products
GET /api/products/{id}
GET /api/products/search
```

Product APIs also support pagination and sorting.

Example:

```text
/api/products?page=0&size=5
/api/products?sort=price,asc
/api/products/search?name=laptop
```

### Cart

```text
POST /api/cart/add
```

Cart operations require JWT authentication.

### Orders

```text
POST /api/orders
GET /api/orders
GET /api/orders/{id}
```

Order operations require JWT authentication.

## Authentication

The application uses JWT-based authentication.

After successful login, the API returns a JWT token. The token can be sent in the Authorization header for protected endpoints.

```text
Authorization: Bearer <JWT_TOKEN>
```

Passwords are stored using BCrypt hashing instead of storing plain-text passwords.

## Order Flow

1. User logs in and receives a JWT token.
2. User sends the JWT token with the order request.
3. The application identifies the authenticated user.
4. The requested product is retrieved.
5. Product stock is validated.
6. The order total is calculated.
7. The order and order item are saved.
8. Product stock is reduced.
9. The transaction is completed.

## Database

The application uses MySQL with Spring Data JPA and Hibernate.

Database configuration uses environment variables for sensitive values:

```properties
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
```

Sensitive credentials are not stored directly in the project configuration.

## API Documentation

Swagger UI is available when the application is running:

```text
http://localhost:8080/swagger-ui/index.html
```

## Tools Used

- IntelliJ IDEA
- MySQL
- DBeaver
- Postman
- Swagger UI
- Git and GitHub