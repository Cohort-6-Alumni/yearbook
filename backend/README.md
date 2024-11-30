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

### Backend

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/yearbook.git
    cd yearbook/backend/yearbook
    ```

2. Copy the `.env.example` to `.env` and configure your environment variables:
    ```sh
    cp .env.example .env
    ```

3. Build the project:
    ```sh
    ./mvnw clean install
    ```

4. Run the application:
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

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details on how to get started.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.