package com.tpolm.microsandbox.web;

import com.tpolm.microsandbox.service.TourRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
    public CollectionModel<RatingDto> getAll() {
        LOGGER.info("GET /ratings");
        return assembler.toCollectionModel(tourRatingService.lookupAll());
    }

    @GetMapping("/{id}")
    public RatingDto getRating(@PathVariable("id") Integer id) {
        LOGGER.info("GET /ratings/{id}", id);
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
