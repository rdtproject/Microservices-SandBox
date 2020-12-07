## DevOps topics
### How Jacoco code coverage works
- https://www.baeldung.com/jacoco
### How to run Spring Boot app from command line
```java
java -Dserver.port=9090 -jar target/microsandbox-0.0.1-SNAPSHOT.jar
```
## Spring MVC vs Spring Data REST
### Spring MVC
- Http Request -> RestController -> CustomService
- Enabled using annotation @RestController
- Any persistence layer can be used, Rest Controller is fowarding http requests to any custom service
- API launches an algorithmic service, not just a data retrieval
- Hide internal data model (entity schema) from the client
```java
@RestController
@RequestMapping(path="/examples")
public class ExampleController {
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Example create(@RequestBody Example example)
}
```
### Spring Data REST
- Http Request -> Spring Data REST -> Spring Data Repository
- Enabled by adding dependency to Spring Data Rest in Maven
- Only for projects / microservices using Spring Data JPA as persistence layes
```java
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```
To avoid exposing as public API
```java
@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk>
```

## Rest response statuses
- 201, created
- 400, bad request
- 404, not found
