# University System (Spring Boot + Thymeleaf)

A multi-module Spring Boot application for university management, with server-rendered Thymeleaf pages for admins, instructors and students. This is the first version of the project. The later version with a JWT-secured REST API and an Angular frontend is in [university-system-angular](https://github.com/lamsalabb/university-system-angular).

## Features

- Three roles (admin, instructor, student), each with its own dashboard and pages
- Registration with email OTP verification and login with Spring Security (BCrypt passwords)
- Course management, enrollment, grading, attendance and fee tracking
- Admin analytics and reports, served by a separate reporting service through an OpenFeign client
- Centralized exception handling

## Modules

| Module | Purpose |
| --- | --- |
| `common-data` | Shared entities and repositories |
| `attendance-lib` | Attendance data access |
| `fee-lib` | Fee data access |
| `core-api` | Main application: controllers, services, security, Thymeleaf views |
| `reporting-api` | Standalone reporting service (port 8081) |
| `reporting-contract` | DTOs shared between `core-api` and `reporting-api` |

## Tech stack

Java 17, Spring Boot, Spring MVC, Spring Security, Spring Data JPA (Hibernate), Thymeleaf, Spring Cloud OpenFeign, MySQL, Maven

## Run locally

1. Create the database, tables and sample data with the scripts in `sql/`.
2. Set your own datasource and mail credentials in each module's `application.properties` (or pass them as environment variables). Do not commit real credentials.
3. Build from the repo root with `mvn clean install`.
4. Start `reporting-api` (port 8081), then `core-api` (port 8080).
5. Open http://localhost:8080
