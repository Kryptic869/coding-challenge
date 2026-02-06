# Jackpot Service - coding-challenge

## Overview

This project is a Java Spring Boot service that manages jackpots and allows users (players) to place bets on those jackpots. The service exposes a custom REST API to create jackpots, place bets, and retrieve recorded wins.
All data is persisted using PostgreSQL as a database.
Moreover, the application can be started with a single command using Docker Compose.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Docker and Docker Compose

---

## Running the Application

By default, Docker Compose uses a prebuilt image from Docker Hub.
For local development, the image can be built from source using an override compose file named `docker-compose.dev.yml`.

The command would therefore be:

```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build
```

### Prerequisites

- Docker
- Docker Compose

### Building and Runnning

From the project root `coding-challenge/jackpot-service`:

```bash
./mvnw clean package
docker compose up --build
```

The API would then be available at: http://localhost:8080.

## Prebuilt Docker Image

A prebuilt Docker Image for this service is also available on Docker Hub.
This allows the application to be run without building the image locally.

The Docker image contains the Spring Boot application only. PostgreSQL needs to be started separately using Docker Compose.

### Pulling the image

```bash
docker pull kryptic869/jackpot-service:latest
```

### Running using Docker Compose

The 'docker-compose.yml' file can be used directly with the prebuilt image via the command:

```bash
docker compose up
```

Alternatively, the service can be built locally from source using the instructions above.

## API Documentation

Once the application is up and running, the OpenAPI documentation is available at:

http://localhost:8080/swagger-ui.html

This page allows you to explore and test all API endpoints interactively via Swagger UI which was added as a dependency in pom.xml.

## Example API Calls

Below you will find an API call example for each API endpoint.

### Creating a Jackpot

### Placing a Bet

### Listing Wins

## Persistence

The application uses PostgreSQL running in a Docker container. This means that database data is persisted using Docker volumes, therefore able to survive container restarts.

## Extra Notes and Assumptions

- Win probability is evaluated per bet.
- Monetary values are handled using 'BigDecimal'.
- For simplicity, authentication and authorisation are not implemented. This means that users (players) can currently also create jackpots, which in the real world would not make sense.
