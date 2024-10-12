
# Kitchensink Application - Spring Boot Migration

This project is a fully functional **Kitchensink** application migrated from a legacy JBoss environment to a modern Spring Boot architecture. The application provides a Member Management system with RESTful endpoints, integrating OpenAPI for documentation, exception handling, and a layered architecture for maintainability.

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)
- [Testing](#testing)
- [Migration Steps](#migration-steps)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

The **Kitchensink** project demonstrates essential features of a Spring Boot application, such as:
- RESTful APIs
- Exception handling using `@ControllerAdvice`
- Service layer encapsulating business logic
- Repository layer for database operations
- OpenAPI integration for API documentation

This project showcases a simple **Member Management** system where users can create, retrieve, update, and delete members through REST endpoints.

---

## Project Structure
```
kitchensink/
├── src/
│   ├── main/
│   │   ├── java/org/spring/as/quickstarts/kitchensink/
│   │   │   ├── controller/           # REST controllers handling requests
│   │   │   ├── entity/               # JPA entities representing data models
│   │   │   ├── exception/            # Custom exception classes
│   │   │   ├── model/                # Request and response models
│   │   │   ├── repository/           # Repository interfaces for data access
│   │   │   ├── service/              # Service layer containing business logic
│   │   │   ├── util/                 # Utility classes for common functionality
│   │   │   └── KitchensinkApplication.java  # Main Spring Boot application class
│   │   └── resources/                # Configuration files and OpenAPI spec
│   └── test/                         # Unit and integration tests
├── pom.xml                           # Maven build file
├── .gitignore                        # Git ignore file
├── migration_steps.md                # Migration guide from JBoss to Spring Boot
└── README.md                         # Project documentation
```

---

## Features
- **Member Management**: Create, retrieve, update, and delete members via REST API.
- **OpenAPI Specification**: Integrated Swagger UI for API documentation and testing.
- **Exception Handling**: Global exception handling using Spring's `@ControllerAdvice`.
- **Layered Architecture**: Clear separation of concerns with Controllers, Services, and Repositories.
- **Maven Build**: Maven is used to manage dependencies and build the application.

---

## Technologies Used

- **Spring Boot**: Framework for building microservices.
- **Spring Data JPA**: For data persistence using the repository pattern.
- **H2 Database**: In-memory database for development and testing.
- **OpenAPI/Swagger**: API documentation and testing.
- **Maven**: Build automation tool.
- **JUnit**: Unit testing framework.

---

## Getting Started

### Prerequisites

- **Java 17+**
- **Maven 3.6+**

### Running the Application

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ankesh-Khatri/kitchensink-backend.git
   cd kitchensink
   ```

2. **Build the project**:
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application**:
   - API Documentation (Swagger UI): [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - H2 Console (Database Access): [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## API Endpoints

| HTTP Method | Endpoint               | Description              |
|-------------|------------------------|--------------------------|
| GET         | /api/members           | Retrieve all members     |
| GET         | /api/members/{id}      | Retrieve member by ID    |
| POST        | /api/members           | Create a new member      |
| PUT         | /api/members/{id}      | Update an existing member|
| DELETE      | /api/members/{id}      | Delete a member by ID    |

All requests and responses adhere to the JSON format. OpenAPI documentation provides a detailed structure of the request/response payloads.

---

## Exception Handling

The application uses centralized exception handling through `@ControllerAdvice`:
- **ResourceNotFoundException**: Thrown when an entity is not found (HTTP 404).
- **GlobalExceptionHandler**: Catches other exceptions and returns appropriate HTTP responses.

Example of error response:
```json
{
  "timestamp": "2024-10-12T12:00:00",
  "message": "Member not found with id 1",
  "details": "uri=/api/members/1"
}
```

---

## Testing

The application includes unit and integration tests to ensure the correctness of the service and controller layers.

- **Unit tests** are found under `src/test/java/...`.
- To run the tests:
  ```bash
  ./mvnw test
  ```

---

## Migration Steps

This project was migrated from a legacy **JBoss** application to **Spring Boot**. The detailed steps of the migration are documented in the `migration_steps.md` file.

### Key Migration Highlights:

1. **EJB to Spring Services**: Replaced EJB session beans with Spring `@Service` beans.
2. **Transactions**: Handled using Spring's `@Transactional` annotation instead of JTA transactions.
3. **Configuration Management**: Moved configurations from JBoss-specific files to `application.yml` in Spring Boot.
4. **Swagger Integration**: Added Swagger/OpenAPI for better API documentation.
5. **Testing**: Refactored tests to use Spring Boot's testing framework with JUnit.

---

## Contributing

Contributions are welcome! Here's how you can contribute:

1. **Fork the repository**.
2. **Create a new branch**:
   ```bash
   git checkout -b feature-branch
   ```
3. **Commit your changes**:
   ```bash
   git commit -m "Add feature"
   ```
4. **Push to the branch**:
   ```bash
   git push origin feature-branch
   ```
5. **Create a Pull Request**.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

For any inquiries, please feel free to reach out:

- **GitHub**: [https://github.com/your-username](https://github.com/your-username)
- **Email**: your-email@example.com
