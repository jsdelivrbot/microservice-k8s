# Microservice framework - K8S / Swagger / Spring Boot / Spring Cloud

## Structure

The modules depend on each other more-or-less this way:
```
common <--+
          |
          +--- libraries/external-script-gradle-plugin
          |
          +--- libraries/app-versioning-gradle-plugin
          |
          +--- test-common
          |
          +--- libraries/oas-generator-template
          |
          +--- libraries/service-base
          |
          +--- contract/api/contract-commons <--+
                                                |
                                                +--- contract/api/external <--+
                                                |                             |          
                                                |                             +--- external-mock
                                                |
                                                +--- contract/api/card-management <--+
                                                                                     |
                                                                                     +--- card-management
```
## Build

Prerequisites:
- Java 8+
- Gradle 4.10.2
- Docker for Windows with Kubernetes enabled (latest)

Go to `./common` and execute `./buildAll.sh`.
Go to `./common` and execute `./helmDeployAll.sh`.