# Manager employee

## Description
The application exposes 2 endpoints:
- one endpoint for creating an employee
- one endpoint for updating the state of an employee

The employee is firstly created with ADDED state.
Afterwards, the state of the employee could be changed by triggering NEXT/PREVIOUS events.
Transition is done with a persisted state machine.

## Technology stack
- Java 11
- Spring Boot
- Spring StateMachine
- MySQL (deployed in AWS)
- H2
- Liquibase
- OpenAPI

## Deployment
### Docker commands
```
mvn spring-boot:build-image
```
```
docker run -it -p9099:8080 manager-employee:0.0.1
```

### OpenAPI
http://localhost:9099/swagger-ui.html