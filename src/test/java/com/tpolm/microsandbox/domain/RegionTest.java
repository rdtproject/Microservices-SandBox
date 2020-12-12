package com.tpolm.microsandbox.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RegionTest {
    @Test
    public void findByLabel() {
        assertThat(Region.CENTRAL_COAST, is(Region.fromLabel("Central Coast")));
        assertThat(Region.NORTHERN_CALIFORNIA, is(Region.fromLabel("Northern California")));
        assertThat(Region.SOUTHERN_CALIFORNIA, is(Region.fromLabel("Southern California")));
        assertThat(Region.VARIES, is(Region.fromLabel("Varies")));
    }

    @Test
    public void getLabel() {
        assertThat(Region.CENTRAL_COAST.getLabel(), is("Central Coast"));
        assertThat(Region.NORTHERN_CALIFORNIA.getLabel(), is("Northern California"));
        assertThat(Region.SOUTHERN_CALIFORNIA.getLabel(), is("Southern California"));
        assertThat(Region.VARIES.getLabel(), is("Varies"));
    }

}