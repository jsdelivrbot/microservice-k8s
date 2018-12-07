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


    ./common/buildAll.sh
    ./common/helmDeployAll.sh

## Local run

Requires docker composer to be installed.

    ./docker-compose-up.sh

This starts one instance of each of the following docker containers:
- zookeeper
- kafka
- rulerunner service

With the following command, you can stop and remove all of the containers (use only locally & with care):

    docker rm -f $(docker ps -a -q)

## Demo

To watch the rulerunner service output, use the following command

    docker logs -f k8s-rulerunner

To feed the dockerized kafka with sample FIX messages, use one of the following commands:

    cd demo

    # TTY compatible bash
    ./run-producer-console-file.sh

    # MinGW compatible bash (under windows)
    ./run-producer-console-file-mingw.sh
