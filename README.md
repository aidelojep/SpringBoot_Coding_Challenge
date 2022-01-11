"# SpringBoot_Coding_Challenge"

TRANSACTION SERVICE APPLICATION

To run with docker do the following
- ./mvnw package
- docker build --tag=transaction-service:latest .
- docker run -p8081:8080 transaction-service:latest

You can now test your application on http://localhost:8081