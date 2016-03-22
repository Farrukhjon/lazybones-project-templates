Project template for developing Spring Boot based REST API.
-----------------------------------------------------------
## Overview

### External dependencies (middleware)
 - MongoDB

### Used tool dependencies
 - Programming languages:
    - Java8 in the production section;
    - Groovy in the testing section.
 - Frameworks:
    - Spring
       - Boot
       - web and web-mvc
       - HATEOAS
       - Data: MongoDB
 - Build automating tool: Gradle, IntelliJ IDEA
 - Testing tools: JUnit, Spock and Cucumber
 - Code quality: Checkstyle, CodeNarc
 - Code coverage: Jacoco
 - Logging: SLF4j (Logback)
 - XML and JSON lib: Jackson
 - Deployment tools: Maven publish, Docker container with pre-installed JDK8 and Ubuntu

### How to build (on linux):
 - for snapshot version just type `./gradlew`
 - for release version, example `./gradlew -Pmajor=1 -Pminor=0 -Ppatch=0 -Pbranch=master`
 - or customize build.sh and launch it.

### How to run
 - ```java -jar spring-boot-rest-api```

