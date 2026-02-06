**Domain Modeling**

3 core entities:

Jackpot

Bet

Win



Jackpot:

* id
* name
* winProbability
* currentSize
* winsCount
* lastWinTimestamp



Bet

* id
* jackpotId
* playerAlias
* amount
* timestamp



Win

* id
* jackpotId
* playerAlias
* amount
* timestamp





**API design (Endpoints)**

|Action|Method|Endpoint|
|-|-|-|
|Create a jackpot|POST|/api/jackpot|
|Get all jackpots|GET|/api/jackpots|
|Create a bet|POST|/api/bet|
|List all wins|GET|/api/wins?limit=x\&from=xyz|





**Business Logic Flow (BetService.java)**

Making a bet flow:

1. Validating the jackpot exists
2. Save bet
3. Add bet amount to jackpot
4. Generate random number
5. If win:
   Save win
   Reset jackpot size
   Increment win counter
6. Save and update jackpot information
7. Return response





**README checklist:**

* Java and Docker requirements
* docker compose up instructions
* Example API calls (either curl or postman)
* OpenAPI link





**Data Transfer Object (DTO)**

The public contract for the API. In DTOs, we define what the API accepts and returns, whilst also decouples the API from the database model (defined in the Model folder). 





Why PostgreSQL is a good choice

* ACID-compliant (important for bets \& wins)
* Widely used in production
* Works perfectly with Spring Data JPA
* Easy to containerize
* Supports future scalability





Maven

Maven is a build and dependency management tool for Java projects.



Why NOT Gradle?

Its is more flexible, but:

* Steeper learning curve
* Less readable for juniors
* Overkill for this challenge



Why NOT Ant?

* Manual configuration
* No dependency resolution
* Outdated



Why Maven is the best choice

* Industry standard
* Declarative and predictable
* Excellent Spring Boot support
* Easy for reviewers to build
* Perfect for Docker pipelines



Maven was chosen for its convention-over-configuration approach and strong integration with Spring Boot.

