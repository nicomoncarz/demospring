# Request Cache

This repository contains a Request Cache webservice built with Java 11, Maven, Spring Boot and Hibernate. Tested with JUnit and Mockito.

## Running instructions
* Linux and MacOS: `./mvnw spring-boot:run` 
* Windows: `mvnw spring-boot:run`

## Architecture decisions
* The DB is generated for each run
* For simplicity reasons is implemented with the default web server (Tomcat)
* In this first version only the get requests are supported.

## Features to add
* Support other HTTP methods, headers and sending a body in a request
* Store incoming cookies for domains
* Manage redirections
* Use a custom database instead of the generated one.

## Future improvements
* Add Domain class
** It will contain a url from the domain, a list of cookies for that domain and a unique Id
* Request
** Add the Domain attribute after creating the Domain class
