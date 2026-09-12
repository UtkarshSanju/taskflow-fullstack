# TaskFlow — Task Management System (Backend)

A RESTful Task Management System built with Spring Boot, developed to learn Spring Boot concepts by building a real project from scratch.

## Features

- **Task Management** — Create, read, update, delete tasks with title, description, status, and due date
- **User Management** — Create, read, update, delete users with role-based accounts (ADMIN / MEMBER)
- **Task Assignment** — Assign and reassign tasks to users (Many-to-One relationship)
- **Status Updates** — Dedicated endpoint to update task status independently (e.g. for a frontend dropdown)
- **Password Management** — Separate endpoint for changing password with a same-password check
- **Validation** — Request validation using Jakarta Bean Validation (`@NotBlank`, `@Email`, `@FutureOrPresent`, etc.)
- **Global Exception Handling** — Centralized handling for not-found errors, validation errors, duplicate-email conflicts, and business rule violations
- **DTO Pattern** — Separate Request/Response DTOs to keep the API contract independent from the database schema and to avoid exposing sensitive fields (e.g. password)

## Tech Stack

- **Java 21**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **MySQL**
- **Lombok**
- **Maven**

## Project Structure

```
src/main/java/com/utkarsh/taskflow/
 ├── controller/    # REST controllers
 ├── service/       # Business logic (interface + implementation)
 ├── repository/    # Spring Data JPA repositories
 ├── entity/        # JPA entities
 ├── dto/           # Request/Response DTOs
 ├── mapper/        # Entity <-> DTO mapping
 ├── enums/         # Status, Role enums
 └── exception/     # Custom exceptions + global exception handler
```

## API Endpoints

### Tasks
| Method | Endpoint | Description |
|---|---|---|
| POST | `/tasks` | Create a task |
| GET | `/tasks` | Get all tasks |
| GET | `/tasks/{id}` | Get a task by id |
| PUT | `/tasks/{id}` | Update a task |
| PATCH | `/tasks/{id}/status` | Update only the task status |
| DELETE | `/tasks/{id}` | Delete a task |

### Users
| Method | Endpoint | Description |
|---|---|---|
| POST | `/users` | Create a user |
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get a user by id |
| PUT | `/users/{id}` | Update user profile (name, email) |
| PUT | `/users/{id}/change-password` | Change password |
| DELETE | `/users/{id}` | Delete a user |

## Running Locally

1. Clone the repo
   ```bash
   git clone <your-repo-url>
   cd taskflow
   ```

2. Create a MySQL database:
   ```sql
   CREATE DATABASE taskflow_db;
   ```

3. Set the following environment variables (do not hardcode credentials in `application.properties`):
   ```
   DB_USERNAME=your_mysql_username
   DB_PASSWORD=your_mysql_password
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

   The app will start on `http://localhost:8080`.

## Roadmap

- [ ] Spring Security + JWT authentication
- [ ] Role-based authorization (ADMIN vs MEMBER permissions)
- [ ] Filtering/search on tasks
- [ ] Pagination
- [ ] React frontend

## Author

Utkarsh Sanju
