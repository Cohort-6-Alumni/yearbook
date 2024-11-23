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

- Java 21 or higher
- Maven 3.6.3 or higher
- Node.js and npm (for frontend)
- PostgreSQL

### Backend

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/yearbook.git
    cd yearbook/backend/yearbook
    ````

3. Set up environment variables in Eclipse:
    1. Open Eclipse and go to your project.
    2. Right-click on your project in the Project Explorer and select **Run As > Run Configurations**.
    3. In the **Run Configurations** dialog, select your application under **Java Application**.
    4. Go to the **Environment** tab.
    5. Click **New** to add a new environment variable.
        - Name: `DEV_URL`
        - Value: `jdbc:postgresql://localhost:5432/yearbook`
    6. Click **New** again to add another environment variable.
        - Name: `DEV_USERNAME`
        - Value: `<your_local_postgress_user>`
    7. Click **New** again to add another environment variable.
        - Name: `DEV_PASSWORD`
        - Value: `<your_pg_password>`
    8. Click **Apply** and then **Run** to save the configuration.

4. Create a local database `yearbook`

5. Run the SQL `creat_tables.sql` to create tables

6. Build the project:
    ```sh
    ./mvnw clean install
    ```

7. Run the application:
    ```sh
    ./mvnw spring-boot:run
    ```

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
    npm start
    ```

## Usage

Once the backend and frontend are running, you can access the application at `http://localhost:3000`. Register a new account or log in with an existing account to start using the digital yearbook.

## Contributing

We welcome contributions! Please see CONTRIBUTING.md for details on how to get started.

## License

This project is licensed under the Apache License 2.0. See the LICENSE file for details.
