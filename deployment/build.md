# Build

## Prerequisites

Java8
Gradle latest
Docker for Windows with Kubernetes
Git bash

Clone all project into a workspace folder.

The file `./common/scripts/init.gradle` must be copied to your GRADLE_USER_HOME directory. [Gradle - Build Environment](https://docs.gradle.org/current/userguide/build_environment.html)
The `init.gradle` must contain the following lines:

- `commonScriptLocation=c:/ ... /common/scripts`
- `artifactory_user=john_doe@acme.com`
- `artifactory_password=******`
- `artifactory_contextUrl=*******`

In all Gradle-based modules the wrapper must be initialized, and later pushed to Git:
gradle wrapper --distribution-type=all --gradle-version=5.0

## Swagger template

This module contains templates used in every API. We should build it first.

- `cd swagger-gen/`
- `gradle uploadArchives`

## Common scripts

Contains lot of gradle scripts like `bill-of-materials.gradle`, `common-rest-contract.gradle`, `static-analysis.gradle` ... etc.

- `cd ../common/`
- `gradle build`

## Contracts

This folder contains the different API definitions. Those contracts are written in swagger. The gradle build script generates the `*-api-def`, `*-java-client-feign`, `*-java-server-feign` subprojects based on the swagger template so that it can be used in other services.

- `cd ../contracts/alfa`
- `gradle install` - we will use it as a dependency in other services

- `cd ../contracts/beta`
- `gradle install`

## Services

Contains two services `alfa-service` and `beta-service`. If you hit the alfa-service's `/alfa` endpoint it will call beta-service's `/beta` endpoint through eureka.

- `cd ../services/`
- `gradle build`
