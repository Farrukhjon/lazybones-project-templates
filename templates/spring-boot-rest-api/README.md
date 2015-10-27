Project template for developing Spring Boot based REST API.
-----------------------------------------------------------
## Overview

### Tool dependencies
 - Programming language: Java8, Groovy 2.4.5
 - Framework: Spring Boot, Spring MVC, Spring HATEOAS.
 - Build automating tool: Gradle, IntelliJ IDEA
 - Testing tools: JUnit, Spock and Cucumber
 - Code quality: Checkstyle, CodeNarc
 - Code coverage: Jacoco
 - Logging: SLF4j, Logback
 - XML and JSON lib: Jackson
 - Deployment tools: Maven publish, Docker container with pre-installed JDK8 and Ubuntu
 -
### How to build:
 - for snapshot version just type `./gradlew`
 - for release version, example `./gradlew -Pmajor=1 -Pminor=0 -Ppatch=0 -Pbranch=master`
 - or customize build.sh and launch it.

