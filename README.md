# Task Management System (****banquemisr.challenge05****)

## Overview

Welcome to the Task Management System repository! This project implements a robust system for managing tasks using a Java-based Spring Boot application with a PostgreSQL database backend.

## A) Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Ensure you have a recent version of Java installed. Download it from [https://www.oracle.com/java/technologies/javase/downloads/](https://www.oracle.com/java/technologies/javase/downloads/).
- **PostgreSQL:** Install and configure PostgreSQL on your system. Refer to the official PostgreSQL documentation for installation instructions: [https://www.postgresql.org/](https://www.postgresql.org/)

### Cloning the Repository

1.  Clone the repository to your local machine:

    ```bash
    git clone https://github.com/minaanwer/banquemisr.challenge05.git
    ```

### Setting up the Development Environment

1.  **Open the Project in Your IDE:**

    - Open the cloned repository in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

2.  **Install Dependencies:**

    - Your IDE should automatically detect and download dependencies. If not, use the following command in your terminal:
      ```bash
      ./gradlew build
      ```

### Configuring the PostgreSQL Database

#### Option 1: Manual Script Execution

1.  **Create the Database:**
    - Use a PostgreSQL client (e.g., pgAdmin, psql) to create a new database named `banquemisr-challenge05`:
      ```sql
      CREATE DATABASE banquemisr-challenge05;
      ```
2.  **Run Database Initialization Scripts:**
    - Execute the following SQL scripts to create tables and seed initial data:
        - `database/1-DDL-DB-Init.sql`
        - `database/seeds/1-sample_users.sql`

#### Option 2: Hibernate Auto-Configuration

1.  **Configure Spring Boot Properties:**
    - Add the following properties to your `application.properties` file:
      ```properties
      spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true  # Optional: Enables logging of generated SQL statements for debugging
      ```

### Running the Application

1.  **Build the Project:**

    - In your terminal, run:
      ```bash
      ./gradlew build
      ```

2.  **Start the Application:**

    - Use your IDE's built-in run configuration or execute the following command in your terminal:
      ```bash
      ./gradlew bootRun
      ```

By following these steps, you should be able to set up and run the Task Management System.

## B) Application Feature Summary

### 1. Task Management Controller
This controller handles the creation, updating, deletion, and retrieval of tasks. It provides endpoints for users to manage their task lifecycle, ensuring that tasks are tracked and completed efficiently.

### 2. Check Health Controller
This is a simple endpoint designed to check a configuration value, confirming to monitoring tools that the application is up and running.
### 3. Scheduler for Upcoming Tasks
The scheduler identifies tasks that are due within one day or less and performs automated actions:
- Sends email notifications to the responsible users.
- Records notifications in the `notification` table for tracking purposes.

### 4. Authentication Controller
This controller manages user authentication and supports the following features:
- Differentiates between **normal users** and **admin users**.
- Assigns multiple roles to each user, enabling flexible and fine-grained access control.

### 5. Route Guard and Role-Based Filters
This feature ensures secure access by validating user authentication and roles:
- Guards routes based on user roles.
- Example:
    - **Task Management Controller**: Accessible by users with the `USER` role.
    - **Check Health Controller**: Accessible only by users with the `ADMIN` role.

## C) Application Test

### 1. Postman Collection
A Postman collection is available for testing the application's API endpoints. It includes predefined requests for the various controllers, such as task management, health check, and authentication. This allows for easy testing of all the critical endpoints to ensure they function as expected.

- Import the provided Postman collection into your Postman workspace to start testing the endpoints.
- The collection includes example requests and expected responses for each of the application's key features.

### 2. Unit Tests with Mocked Data
Unit tests with mocked data are implemented to verify the functionality of the application’s components without requiring access to real databases or external services. These tests use mocked versions of dependencies, such as repositories and services, to isolate the logic and ensure the correct behavior of the controllers and services.

- The tests simulate various scenarios and assert the correctness of the application's business logic.

### 3. Unit Tests with Real Data
Unit tests with real data are designed to verify the application's functionality with actual data from the database. These tests require a running instance of the database and ensure that the application correctly handles real-world data scenarios.

- The tests are integrated into the application’s CI/CD pipeline to ensure that each new change is validated against a real environment.
