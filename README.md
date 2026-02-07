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

The `docker-compose.yml` file can be used directly with the prebuilt image via the command:

```bash
docker compose up
```

Alternatively, the service can be built locally from source using the instructions above.

## API Documentation

Once the application is up and running, the OpenAPI documentation is available at:

http://localhost:8080/swagger-ui.html

This page allows you to explore and test all API endpoints interactively via Swagger UI, which was added as a dependency in pom.xml.

### Health and Monitoring

The services exposes healt and metric endpoints using Spring Boot Actuator:
- `GET /actuator/health`
- `GET /actuator/metrics`

### Example API Calls

Below you will find an API call example for each API endpoint.
The [Listing Wins](#listing-wins) section also shows the `/wins` endpoint with different types of limiting and filtering. 
These can be of any combination due to how `WinService.java` was created. This was discovered during manual testing. 

#### Creating a Jackpot
<img width="993" height="653" alt="Screenshot 2026-02-07 152843" src="https://github.com/user-attachments/assets/1d7c67f7-5543-4552-bf53-5cd1349a800a" />

#### Listing all Jackpots
<img width="1055" height="802" alt="Screenshot 2026-02-07 153659" src="https://github.com/user-attachments/assets/80580b63-b413-47fd-95f5-1321f19618aa" />

#### Placing a Bet on a specific jackpot
<img width="1055" height="575" alt="Screenshot 2026-02-07 153759" src="https://github.com/user-attachments/assets/25e2fe95-c937-446f-8a87-c0fdb9d8a512" />

#### Listing Wins
<img width="1129" height="811" alt="Screenshot 2026-02-07 153838" src="https://github.com/user-attachments/assets/9a2c5614-fdd3-4548-9401-13045cc38ad4" />

##### Listing Wins (With Limiting)
<img width="1169" height="581" alt="image" src="https://github.com/user-attachments/assets/6a9ecfd7-f783-4296-bd3e-2d7029ae9100" />

##### Listing Wins (With Filtering [from])
<img width="1171" height="600" alt="image" src="https://github.com/user-attachments/assets/a7619664-89b8-44a3-9e57-c6faca9618b5" />

##### Listing Wins (With Filtering [to])
<img width="1176" height="623" alt="image" src="https://github.com/user-attachments/assets/f2d1b331-2042-4d1c-bb79-b9aa24157f88" />

##### Listing Wins (With Limiting and Filtering [to])
<img width="1285" height="542" alt="image" src="https://github.com/user-attachments/assets/79e2c177-6832-43fe-8068-85b666b3b4eb" />

#### Health & Monitoring
<img width="523" height="470" alt="image" src="https://github.com/user-attachments/assets/423849ba-941a-43fa-84c0-1562d5b81162" />
<img width="526" height="833" alt="image" src="https://github.com/user-attachments/assets/f9555778-d157-483a-a91c-834c846b9e4c" />

## Persistence

The application uses PostgreSQL running in a Docker container. This means that database data is persisted using Docker volumes, therefore able to survive container restarts.

## Extra Notes and Assumptions

- Win probability is evaluated per bet.
- Monetary values are handled using `BigDecimal`.
- For simplicity, authentication and authorisation are not implemented. This means that users (players) can currently also create jackpots, which in the real world would not make sense.
