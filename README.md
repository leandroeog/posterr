# posterr

This project simulates some Twitter functionalities for Strider

### What was used?

* Java 11
* Spring Boot
* Lombok
* H2 Database
* Gradle


* DDD concepts
* Clean Arch concepts

### How to run it?

Posterr is a basic project without external dependencies, 
so you can import it using your favorite IDE and/or run it as any other Spring Boot project

### Endpoints

[Postman](Posterr.postman_collection.json)

###### PS: users are hardcoded, so main functions works around a default user, except for reposting

### Critique

#### What could be improved?

* Read and write database separation
* Add cache strategies
* Refine API exceptions and validations
* Enhance fake data usage

#### Scaling
##### At some point the app will hang during transactions with TPS number increasing, specially at posts creation. So we could:
* Run a project containerization
* Split project into at least two microservices (timeline-service and post-service) 
* Apply a cloud infra for horizontal scaling
* In addition to adopting CQRS strategy, separate database and app using something like RDS
* Create database indexes and NoSQL approaches
* Use cache
* Analyse distributed storages like Hadoop
* CDN config by region for low latency
