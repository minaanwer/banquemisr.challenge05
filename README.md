# Task Management System (*banquemisr.challenge05*)

## Overview

Welcome to the Task Management System repository! This project implements a robust system for managing tasks using a Java-based Spring Boot application with a PostgreSQL database backend.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Ensure you have a recent version of Java installed. Download it from [https://www.oracle.com/java/technologies/javase/downloads/](https://www.google.com/url?sa=E&source=gmail&q=https://www.oracle.com/java/technologies/javase/downloads/).
- **PostgreSQL:** Install and configure PostgreSQL on your system. Refer to the official PostgreSQL documentation for installation instructions: [https://www.postgresql.org/](https://www.google.com/url?sa=E&source=gmail&q=https://www.postgresql.org/)

### Cloning the Repository

1.  Clone the repository to your local machine:

    ```bash
    git clone [https://github.com/minaanwer/banquemisr.challenge05.git](https://github.com/minaanwer/banquemisr.challenge05.git)
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
      spring.jpa.show-sql=true 1   # Optional: Enables logging of generated SQL statements for debugging
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