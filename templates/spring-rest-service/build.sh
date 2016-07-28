#!/bin/bash
./gradlew -Pmajor=1 -Pminor=0 -Ppatch=0 -Pbranch=master -Pname=spring-rest-service -PprojectDescription="Spring Boot based REST API" -PrunIntegrationTests=true --stacktrace
