# Library Management System — Spring Boot

A Java 21 and Spring Boot backend project for learning REST API development, persistence with Spring Data JPA, MySQL integration, request validation, and authentication/security concepts.

<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?style=flat-square&logo=springboot&logoColor=white" alt="Spring Boot 3.5.6" />
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=flat-square&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apachemaven&logoColor=white" alt="Maven" />
</p>

## Overview

The application is configured as a Spring Boot service with Spring Web MVC, Spring Data JPA, Spring Security, JWT libraries, Bean Validation, MySQL, and Actuator dependencies. The goal is to practice building a maintainable Java backend backed by a relational database.

## Technology Stack

- Java 21
- Spring Boot 3.5.6
- Spring Web MVC
- Spring Data JPA / Hibernate
- Spring Security and JSON Web Token (JWT) libraries
- MySQL Connector/J
- Jakarta Bean Validation
- Maven

## Prerequisites

- JDK 21
- MySQL Server
- Maven, or the Maven wrapper if one is present in the project
- An IDE such as IntelliJ IDEA or VS Code

## Configure Locally

1. Create a MySQL database for the application. The default configured database name is `Library_db`.
   ```sql
   CREATE DATABASE Library_db;
   ```

2. Set these environment variables in your IDE run configuration or shell. Do not put real credentials in committed files.

   | Variable | Purpose |
   |---|---|
   | `DB_URL` | JDBC URL; defaults to `jdbc:mysql://localhost:3306/Library_db` |
   | `DB_USERNAME` | MySQL username; defaults to `root` for local development |
   | `DB_PASSWORD` | MySQL password; defaults to an empty value |
   | `JWT_SECRET` | Required secret used to sign JWTs; use a long, cryptographically random secret |
   | `JWT_EXPIRATION` | Token lifetime in milliseconds; defaults to `86400000` |
   | `SERVER_PORT` | HTTP port; defaults to `8080` |

   Example for PowerShell (replace the placeholder with a locally generated secret; do not commit it):
   ```powershell
   $env:DB_USERNAME = "root"
   $env:DB_PASSWORD = "your-local-mysql-password"
   $env:JWT_SECRET = "replace-with-a-long-random-secret"
   ```

3. Open a terminal in this directory and start the application:
   ```bash
   mvn spring-boot:run
   ```

4. The default server port is `8080`. Use the controller mappings in the source code to identify the available API endpoints.

## Security Notes

- Keep database passwords and JWT signing secrets out of Git.
- The committed application configuration now reads sensitive values from environment variables.
- A database credential and JWT secret were previously present in the tracked configuration. **Rotate both values immediately** in your local MySQL setup and any environment where they were used. Removing them from the latest file does not remove them from Git history.
- Do not use `ddl-auto=update` as a substitute for reviewed database migrations in production.
- Review authentication, authorization, CORS, token expiry, and secret management before deploying publicly.

## Engineering Roadmap

- Add automated tests for service logic, validation, and security rules
- Document the real API endpoints with sample requests and responses
- Add database migrations (for example, Flyway) and seed data for development
- Add structured logging and consistent API error responses
- Add OpenAPI/Swagger documentation after verifying the controller mappings
- Add CI checks for compilation, tests, and dependency vulnerabilities

## Author

**Junaid Khan** — Software Engineering student at COMSATS University, Sahiwal.

- GitHub: [@junaidkhan051](https://github.com/junaidkhan051)
- LinkedIn: [Junaid Khan](https://www.linkedin.com/in/junaid-khan-46b765319/)
