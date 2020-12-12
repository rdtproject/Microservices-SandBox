package com.tpolm.microsandbox.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class TourTest {

    @Test
    void testConstructorAndGetters() {
        TourPackage p = new TourPackage("CC", "name");
        Tour tour = new Tour("title", "description", "blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.DIFFICULT, Region.CENTRAL_COAST);
        assertNull(tour.getId());
        assertThat(tour.getTitle(), is("title"));
        assertThat(tour.getDescription(), is("description"));
        assertThat(tour.getBlurb(), is("blurb"));
        assertThat(tour.getPrice(), is(50));
        assertThat(tour.getDuration(), is("1 day"));
        assertThat(tour.getBullets(), is("bullet"));
        assertThat(tour.getKeywords(), is("keywords"));
        assertThat(tour.getTourPackage().getCode(), is("CC"));
        assertThat(tour.getDifficulty(), is(Difficulty.DIFFICULT));
        assertThat(tour.getRegion(), is(Region.CENTRAL_COAST));
    }

    @Test
    void equalsHashcodeVerify() {
        TourPackage p = new TourPackage("CC", "name");
        Tour tour1 = new Tour("title", "description", "blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.DIFFICULT, Region.CENTRAL_COAST);
        Tour tour2 = new Tour("title", "description", "blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.DIFFICULT, Region.CENTRAL_COAST);

        assertThat(tour1, is(tour2));
        assertThat(tour1.hashCode(), is(tour2.hashCode()));
    }

}