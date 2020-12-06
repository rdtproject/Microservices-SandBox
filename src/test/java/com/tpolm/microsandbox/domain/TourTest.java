package com.tpolm.microsandbox.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourTest {

    @Test
    void createAndGetTour_test_if_constructor_works_fine() {
        final String TITLE = "Bratislavala tour";
        final String DESCRIPTION = "Visit Bratislava and have a lot of fun";
        final String BLURB = "Blurb";
        final Integer PRICE = 100;
        final String DURATION = "4 hours";
        final String BULLETS = "Some visiting bullets";
        final String KEYWORDS = "some keywords";
        final TourPackage TOUR_PACKAGE = new TourPackage("code", "name");
        final Difficulty DIFFICULTY = Difficulty.EASY;
        final Region REGION = Region.CENTRAL_COAST;

        Tour t = new Tour(TITLE, DESCRIPTION, BLURB, PRICE, DURATION, BULLETS,
                KEYWORDS, TOUR_PACKAGE, DIFFICULTY, REGION);

        assertEquals(TITLE, t.getTitle());
        assertEquals(DESCRIPTION, t.getDescription());
        assertEquals(BLURB, t.getBlurb());
        assertEquals(PRICE, t.getPrice());
        assertEquals(DURATION, t.getDuration());
        assertEquals(BULLETS, t.getBullets());
        assertEquals(KEYWORDS, t.getKeywords());
        assertEquals(TOUR_PACKAGE, t.getTourPackage());
        assertEquals(REGION, t.getRegion());
    }

}