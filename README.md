## How to run Spring Boot app from command line
```java
java -Dserver.port=9090 -jar target/microsandbox-0.0.1-SNAPSHOT.jar
```
## Spring MVC vs Spring Data REST
### Spring MVC
- Http Request -> RestController -> CustomService
### Spring Data REST
- Http Request -> Spring Data REST -> Spring Data Repository
