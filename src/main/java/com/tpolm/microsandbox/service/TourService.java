package com.tpolm.microsandbox.service;

import com.tpolm.microsandbox.domain.Difficulty;
import com.tpolm.microsandbox.domain.Region;
import com.tpolm.microsandbox.domain.Tour;
import com.tpolm.microsandbox.domain.TourPackage;
import com.tpolm.microsandbox.repository.TourPackageRepository;
import com.tpolm.microsandbox.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

        private TourRepository tourRepository;
        private TourPackageRepository tourPackageRepository;

        @Autowired
        public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
            this.tourRepository = tourRepository;
            this.tourPackageRepository = tourPackageRepository;
        }

        public Tour createTour(String title, String description, String blurb, Integer price,
                               String duration, String bullets,
                               String keywords, String tourPackageName, Difficulty difficulty, Region region) {

            TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(() ->
                    new RuntimeException("Tour package does not exist: {}" + tourPackageName));

            return tourRepository.save(new Tour(title, description, blurb, price, duration,
                    bullets, keywords, tourPackage, difficulty, region));
        }

    }
