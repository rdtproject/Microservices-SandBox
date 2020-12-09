package com.tpolm.microsandbox.web;

import com.tpolm.microsandbox.domain.TourRating;

import javax.validation.constraints.*;

public class RatingDto {

    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 255)
    @NotEmpty(message = "Please provide a comment")
    private String comment;

    @NotNull
    private Integer customerId;

    RatingDto(TourRating tourRating) {
        this(tourRating.getScore(), tourRating.getComment(), tourRating.getPk().getCustomerId());
    }

    private RatingDto(Integer score, String comment, Integer customerId) {
        this.score = score;
        this.comment = comment;
        this.customerId = customerId;
    }

    protected RatingDto() {}

    Integer getScore() {
        return score;
    }

    String getComment() {
        return comment;
    }

    Integer getCustomerId() {
        return customerId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
