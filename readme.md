# Username Registration and Login

A small Spring Boot application with registration, login, and a protected account page. Usernames and BCrypt-hashed passwords are stored in a local H2 database at `data/users`.

## Run locally

Requires Java 11 and Maven.

```sh
mvn spring-boot:run
```

Open `http://localhost:8080`, create an account, and sign in. The H2 database file is created automatically and persists between restarts.

## Build and run with Docker

```sh
docker build -t username-login .
docker run -p 8080:8080 -v account-data:/work/data username-login
```

The named volume keeps the H2 database when the container is replaced.
