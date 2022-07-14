# blog-app
Blog app using micronaut framework with kotlin and postgresql for database.

## Project Structure

1. api : Exposes Api For the Client to Interact with Various patient.
2. event : Listens and stores data in Postgres database

## Local setup:
Required tools:
1. Postgres server
2. Gradle

### Running API locally:

To stay upgraded with database:
    a. ` ./gradlew migration:run`

To run the server:

    a. ` ./gradlew runApi `
    
    b. Hit using following endpoint `http://localhost:9000/api/v1/`
