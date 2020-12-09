package com.tpolm.microsandbox.web;

import com.tpolm.microsandbox.domain.TourRating;

import javax.validation.constraints.*;

public class RatingDto {

    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 16)
    @NotEmpty(message = "Please provide a comment")
    private String comment;

    @NotNull
    private Integer customerId;

    public RatingDto(TourRating tourRating) {
        this(tourRating.getScore(), tourRating.getComment(), tourRating.getPk().getCustomerId());
    }

    private RatingDto(Integer score, String comment, Integer customerId) {
        this.score = score;
        this.comment = comment;
        this.customerId = customerId;
    }

    protected RatingDto() {}

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Integer getCustomerId() {
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
