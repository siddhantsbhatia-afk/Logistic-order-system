# Logistic-order-system
Implemented a backend service using a Layered Architecture (Controller, Service, Repository).

## Setup Instructions
1. **Database:** Ensure PostgreSQL is installed.
   - Run: `CREATE DATABASE logdb;`
   - Run: `CREATE USER loguser WITH PASSWORD 'logpass';`
   - Run: `GRANT ALL ON SCHEMA public TO loguser;`
2. **Java Version:** Developed using OpenJDK 21/26.
3. **Build & Run:** Open in IntelliJ and run `LogisticsOrderSystemApplication`.

## API Documentation
The API is documented using **Swagger/OpenAPI**.
- **Testing Interface:** http://localhost:8080/swagger-ui/index.html
