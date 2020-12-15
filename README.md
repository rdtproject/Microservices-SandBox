## Next courses
- https://courses.in28minutes.com/courses/in28minutes-course-guide/lectures/8919269
- https://www.udemy.com/course/microservices-ddd/

## Frameworks for Microservices
- https://micronaut.io/
- https://quarkus.io/
- https://www.elastic.co/what-is/elk-stack

## Quick apps in JS from HitHub pages
- https://labmonkey.github.io/angularjs-pdf-generator/
- https://dev.to/yuribenjamin/how-to-deploy-react-app-in-github-pages-2a1f

## DevOps topics
### How Jacoco code coverage works and what is the correlation with ASM code manipulation framework
- https://www.baeldung.com/jacoco
## How to run
- Spring Boot app from command line: java -Dserver.port=9090 -jar target/microsandbox-0.0.1-SNAPSHOT.jar
- Swagger: http://localhost:8081/swagger-ui/
- MailHog: http://192.168.1.2:32778/
- Jenkins: http://192.168.1.2:8090/
- Create MySql Docker image: 
```console
sudo docker run  --detach   --name microsandbox-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password
-e MYSQL_DATABASE=microsandbox -e MYSQL_USER=rdt -e MYSQL_PASSWORD=rdt_pass -d mysql
```
- Remove Docker container: sudo docker rm microsandbox-mysql
## HATEOAS 1.0
- https://www.baeldung.com/spring-hateoas-tutorial
- https://docs.spring.io/spring-hateoas/docs/current/reference/html/#reference

### RESTful example http calls
Testing of HATEOAS feature (RatingController)
```java
http://localhost:8080/ratings
```
```java
@GetMapping
public CollectionModel<RatingDto> getAll() {
	return assembler.toCollectionModel(tourRatingService.lookupAll());
}

@GetMapping("/{id}")
public RatingDto getRating(@PathVariable("id") Integer id) {
	return assembler.toModel(tourRatingService.lookupRatingById(id)
	  	.orElseThrow(() -> new NoSuchElementException("Rating " + id + " not found"))
)
```
Testing of HATEOAS plus paging feature
```java
http://localhost:8080/tours/1/ratings?page=0&size=2
```
```java
    @GetMapping
    public PagedModel<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId, Pageable pageable, 
    		PagedResourcesAssembler pagedAssembler) {
		
        Page<TourRating> tourRatingPage = tourRatingService.lookupRatings(tourId, pageable);
        return pagedAssembler.toModel(tourRatingPage, ratingAssembler);
    }
```
## Logging
Testing if logging works correct
```java
http://localhost:8080/tours/3/ratings/3?customers=4,5,6,7,3,8
```
After executing twice - constrant error
## Spring REST Validation
By default Bean Validation in Rest is returning not really useful response
This article explains how to provide more useful Rest http respone (which Bean attributes did not pass which validation rules)
- https://mkyong.com/spring-boot/spring-rest-validation-example/
To enable Rest Bean Validation, Maven dependency:
```java
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
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
