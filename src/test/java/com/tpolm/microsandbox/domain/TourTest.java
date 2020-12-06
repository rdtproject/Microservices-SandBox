package com.tpolm.microsandbox.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourTest {

    final static String TITLE = "Bratislavala tour";
    final static String DESCRIPTION = "Visit Bratislava and have a lot of fun";
    final static String BLURB = "Blurb";
    final static Integer PRICE = 100;
    final static String DURATION = "4 hours";
    final static String BULLETS = "Some visiting bullets";
    final static String KEYWORDS = "some keywords";
    final static TourPackage TOUR_PACKAGE = new TourPackage("code", "name");
    final static Difficulty DIFFICULTY = Difficulty.EASY;
    final static Region REGION = Region.CENTRAL_COAST;


    @Test
    void createAndGetTour_test_if_constructor_works_fine() {
        Tour t = new Tour(TITLE, DESCRIPTION, BLURB, PRICE, DURATION, BULLETS,
                KEYWORDS, TOUR_PACKAGE, DIFFICULTY, REGION);

        assertIfAllAttributesAreCorrect(t);
    }

    @Test
    void createAndGetTour_test_getters_and_setters() {
        Tour t = new Tour();
        t.setTitle(TITLE);
        t.setDescription(DESCRIPTION);
        t.setBlurb(BLURB);
        t.setPrice(PRICE);
        t.setDuration(DURATION);
        t.setBullets(BULLETS);
        t.setKeywords(KEYWORDS);
        t.setTourPackage(TOUR_PACKAGE);
        t.setRegion(REGION);

        assertIfAllAttributesAreCorrect(t);
    }

    private void assertIfAllAttributesAreCorrect(Tour t) {
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