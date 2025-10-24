# Inventory Control Project

This repository contains the code for developing an inventory control system. The project will be implemented in multiple stages, during which additional functionalities and features will be added.

## Project Stages

### Stage 1: User service

In this first stage, an authentication and authorization service will be implemented using **Spring Security** with **OAuth2**. This service will allow users to log in securely and manage their permissions within the system.

### Stage 2: Product service and Api Gateway

In the second instance, the product service and API gateway were implemented. Both are secure and were configured as **OAuth2 resource servers**.

### Stage 3: Inventory service

In stage 3, the Inventory Service was implemented. As mentioned above, this is also an **OAuth2 resource server**. It was also configured with **OpenFeign** to communicate with the Product Service. 
All services use SQL with JPA. 

### Stage 4: Eureka Server

In the fourth stage, the Eureka Server microservice was added.  Spring Boot Actuator was also added to view the status/health of each of the services. 

### Stage 5: Docker

In the fifth instance, the applications and database were dockerized.

## Repository Structure

The repository is organized into the following folders:

- **/scripts**: Contains the scripts necessary to create the database. It includes some predefined users to facilitate testing of the system.

- **/postman-collections**: Includes Postman collections that allow testing of the different APIs available in the login service. This will help developers verify the functionality of the system more efficiently.

- **/microservices**: This section defines all the microservices included in the project with their correct configurations.

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/debigonz/inventoryControlService.git

2. **Install the required tools**:

Before you start, make sure you have the following installed:

- **Java 21**: The project is built using Java 21. Ensure you have the JDK installed.
- **Maven**: This project uses Maven for dependency management. Install Maven if you haven't already.
- **IntelliJ IDEA**: A recommended IDE for developing Java applications. You can use other IDEs, but IntelliJ provides great support for Spring projects.
- **MySQL**: Install MySQL to manage the database for the application.
- **Postman**: Used for testing the API endpoints.