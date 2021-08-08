# Request Cache

This repository contains a Request Cache webservice built with Java 11, Maven, Spring Boot and Hibernate. Tested with JUnit and Mockito.

## Running instructions
* Linux and MacOS: `./mvnw spring-boot:run` 
* Windows: `mvnw spring-boot:run`

## Architecture decisions
* The DB is generated for each run
* For simplicity reasons is implemented with the default web server (Tomcat)
* In this first version only the get requests are supported.
