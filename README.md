## How to run Spring Boot app from command line
```java
java -Dserver.port=9090 -jar target/microsandbox-0.0.1-SNAPSHOT.jar
```
## Spring MVC vs Spring Data REST
### Spring MVC
- Http Request -> RestController -> CustomService
- Enabled using annotation @RestController
- Any persistence layer can be used, Rest Controller is fowarding http requests to any custom service
### Spring Data REST
- Http Request -> Spring Data REST -> Spring Data Repository
- Enabled by adding dependency to Spring Data Rest in Maven
- Only for projects / microservices using Spring Data JPA as persistence layes
