package com.tpolm.microsandbox.web;

import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.*;
import java.util.Objects;

public class RatingDto extends RepresentationModel<RatingDto> {

    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 255)
    private String comment;

    @NotNull
    private Integer customerId;

    public RatingDto(Integer score, String comment, Integer customerId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RatingDto ratingDto = (RatingDto) o;
        return Objects.equals(score, ratingDto.score) &&
                Objects.equals(comment, ratingDto.comment) &&
                Objects.equals(customerId, ratingDto.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score, comment, customerId);
    }
}
