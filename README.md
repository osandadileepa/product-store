# Getting Started

Application to show product price details and to calculate product price based on the UNIT price of a specific product.

# Build and Deployment instruction

## Development Environment setup

Make sure following are available

- JDK 1.8
- Docker and Docker Compose
- Node 12.13.1
- Angular CLI 8.3.xxx
- PostgreSQL 12.4


## PostgreSQL instance details

- PostgreSQL instance should be exposed in localhost in port 5432
- Default database store should be created with the credentials available in the yml file
- jdbc:postgresql://localhost:5432/store

## Application source and development environment

- Backend available at src/main/java
- Frontend available at src/main/webapp
- MySQL instance will be available by using docker-compose file at src/main/docker(command : docker-compose up)
- Backend will be available by using following command at the project root.
```bash
SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun
```
- Frontend will be served at http://localhost:4200 by executing following command at src/main/webapp

```bash
ng serve
```

## Build application binary

In the project root directory execute the following bach script

- Angular CLI above mentioned version should be available globally in order to build to work.

```bash
./build.sh
```

## Start the application

- MySQL instance should available at locally, port 3306 with root username and root password in order to jar file to work.

```bash
java -jar -Dspring.profiles.active=dev build/libs/product-store-0.0.1-SNAPSHOT.jar
```

After executing above command application present at http://localhost:8080

