package com.tpolm.microsandbox.web;

import com.tpolm.microsandbox.service.TourRatingService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@Api("API to collect all ratings")
@RestController
@RequestMapping(path = "/ratings")
public class RatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);
    private TourRatingService tourRatingService;
    private RatingAssembler assembler;

    @Autowired
    public RatingController(TourRatingService tourRatingService, RatingAssembler assembler) {
        this.tourRatingService = tourRatingService;
        this.assembler = assembler;
    }

    @GetMapping
    @ApiOperation("Find all ratings")
    @ApiResponse(code = 200, message = "OK")
    public Collection<RatingDto> getAll() {
        LOGGER.info("GET /ratings");
        return assembler.toCollectionModel(tourRatingService.lookupAll()).getContent();
    }

    @GetMapping("/{id}")
    @ApiOperation("Find ratings by id")
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Rating not found")})
    public RatingDto getRating(@ApiParam(value = "Rating identifier - custom parameter description")
                                   @PathVariable("id") Integer id) {
        LOGGER.info("GET /ratings/{}", id);
        return assembler.toModel(tourRatingService.lookupRatingById(id)
                .orElseThrow(() -> new NoSuchElementException("Rating " + id + " not found"))
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.info("Handled NoSuchElementException");
        return ex.getMessage();
    }
}
