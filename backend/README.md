# Yearbook

Digital Yearbook for Obsidi Alumni

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Yearbook project is a digital yearbook application for Obsidi Alumni. It is built using Spring Boot for the backend and a frontend framework (to be specified).

## Features

- User authentication and authorization
- Profile management
- Yearbook entries with text and images
- Search functionality
- Admin panel for managing users and entries

## Installation

### Prerequisites

- Java 21
- Maven 3.6.3 or higher
- Node.js and npm (for frontend)
- PostgreSQL

### Backend

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/yearbook.git
   cd yearbook/backend/yearbook
   ```

2. Set up environment variables in Eclipse:

   1. Open Eclipse and go to your project.
   2. Right-click on your project in the Project Explorer and select **Run As > Run Configurations**.
   3. In the **Run Configurations** dialog, select your application under **Java Application**.
   4. Go to the **Environment** tab.
   5. Click **New** to add a new environment variable.
      - Name: `DEV_URL`
      - Value: `jdbc:postgresql://localhost:5432/yearbook`
   6. Click **New** again to add another environment variable.
      - Name: `DEV_USERNAME`
      - Value: `<your_local_postgres_user>`
   7. Click **New** again to add another environment variable.
      - Name: `DEV_PASSWORD`
      - Value: `<your_pg_password>`
   8. Click **Apply** and then **Run** to save the configuration.

3. Create a local database `yearbook`

4. Run the SQL `creat_tables.sql` to create tables

5. Build the project:

   ```sh
   ./mvnw clean install
   ```

6. Run the application:
   `sh
    ./mvnw spring-boot:run
    `
   Here are the updated instructions, including how to fix errors:

### Setting up Pre-commit Hook with Spotless

To ensure code quality and consistent formatting, we use the Spotless plugin with Maven. Follow these steps to set up a pre-commit hook using `mvnw`:

1. Navigate to your project's root directory.
2. Run this command
   ```sh
   ./mvnw clean install -DskipTests
   ```
3. If you are on windows, ensure you are using Git Bash.

### Fixing Spotless Errors

If the Spotless check fails, you can fix the formatting issues by running:

```sh
./mvnw spotless:apply
```

This will automatically format your code according to the project's standards. This is already configured in the pre-commit hook.

### Frontend

1. Navigate to the frontend directory:

   ```sh
   cd yearbook/frontend
   ```

2. Install dependencies:

   ```sh
   npm install
   ```

3. Start the development server:
   ```sh
   npm run dev
   ```

## Usage

Once the backend and frontend are running, you can access the application at `http://localhost:3000`. Register a new account or log in with an existing account to start using the digital yearbook.

## Contributing

We welcome contributions! Please see [team wiki](https://github.com/Cohort-6-Alumni/yearbook/wiki/How-to-contribute-to-the-yearbook-repo) for details on how to get started.
