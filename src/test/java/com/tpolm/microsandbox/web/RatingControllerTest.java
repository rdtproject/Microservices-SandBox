package com.tpolm.microsandbox.web;

import com.tpolm.microsandbox.domain.Tour;
import com.tpolm.microsandbox.domain.TourRating;
import com.tpolm.microsandbox.service.TourRatingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RatingControllerTest {

    private static final String RATINGS_URL = "/ratings";

    // These ids do not already exist in DB
    private static final int TOUR_ID = 999;
    private static final int RATING_ID = 555;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT= "comment";

    @MockBean
    private TourRatingService tourRatingServiceMock;

    @Mock
    private TourRating tourRatingMock;

    @Mock
    private Tour tourMock;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setupReturnValuesOfMockedMethods() {
        when(tourRatingMock.getTour()).thenReturn(tourMock);
        when(tourMock.getId()).thenReturn(TOUR_ID);
        when(tourRatingMock.getComment()).thenReturn(COMMENT);
        when(tourRatingMock.getScore()).thenReturn(SCORE);
        when(tourRatingMock.getCustomerId()).thenReturn(CUSTOMER_ID);
    }

    @Test
    public void getRatings() {
        when(tourRatingServiceMock.lookupAll()).thenReturn(Arrays.asList(tourRatingMock, tourRatingMock, tourRatingMock));
        ResponseEntity<List<RatingDto>> response = restTemplate.exchange(RATINGS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RatingDto>>() {
                });
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(3));
    }

}
