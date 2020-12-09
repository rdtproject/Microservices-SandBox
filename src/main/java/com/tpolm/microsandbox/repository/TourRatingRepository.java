package com.tpolm.microsandbox.repository;

import com.tpolm.microsandbox.domain.TourRating;
import com.tpolm.microsandbox.domain.TourRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

// hidden from public access to Spring Data Rest service
@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Integer tourId);

    Page<TourRating> findByPkTourId(Integer tourId, Pageable pageable);

    Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
}