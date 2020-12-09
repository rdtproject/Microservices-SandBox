## DevOps topics
### How Jacoco code coverage works
- https://www.baeldung.com/jacoco
### How to run Spring Boot app from command line
```java
java -Dserver.port=9090 -jar target/microsandbox-0.0.1-SNAPSHOT.jar
```
## Spring REST Validation
- https://mkyong.com/spring-boot/spring-rest-validation-example/

## Put vs Patch operation
- Put: all attributes are updated
- Patch: only selected attributes are updated

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
## Exception handling
In Rest controller class throw an exception for the scenario as below.
```java
private Tour verifyTour(int tourId) throws NoSuchElementException {
	return tourRepository.findById(tourId).orElseThrow(() -> new NoSuchElementException("Tour does not exist " + tourId));
}
```
In Rest controller class catch any exception of this type and return Rest response status.
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(NoSuchElementException.class)
public String return400(NoSuchElementException ex) {
	return ex.getMessage();
}
```
## Path variable vs RequestBody
Having Rest controller
```java
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
}
```
- It can be executed as e.g.: "localhost:8080/tours/3/ratings"
And having method of type Post
```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody @Validated RatingDto ratingDto) {
Tour tour = verifyTour(tourId);
tourRatingRepository.save(new TourRating( new TourRatingPk(tour, ratingDto.getCustomerId()),
	ratingDto.getScore(), ratingDto.getComment()));
}
```
- Method will return response CREATED
- Parameter tourId is of type PathVariable (part of: @RequestMapping(path = "/tours/{tourId}/ratings"))
- Parameter ratingDto is of type @RequestBody as this is Dto object passed in the Post request
- @Validated enforces checking Java Bean Validation rules on the Dto object
## Rest response statuses
- 201, created
- 400, bad request
- 404, not found
