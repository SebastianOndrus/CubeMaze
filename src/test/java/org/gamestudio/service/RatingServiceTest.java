package org.gamestudio.service;

import org.gamestudio.entity.Rating;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class RatingServiceTest {

    private final RatingService ratingService = new RatingServiceJDBC();

    @Test
    public void testReset() {
        ratingService.reset();

        assertEquals(0,ratingService.getAverageRating("cubeMaze"));
    }

    @Test
    public void testSetRatingWithUpdate() {
        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));
        ratingService.setRating(new Rating("Viktoria","cubeMaze",4,new Date()));

        assertEquals(2,ratingService.getRating("cubeMaze","Marcel"));
        assertEquals(4,ratingService.getRating("cubeMaze","Viktoria"));

        ratingService.setRating(new Rating("Marcel","cubeMaze",3,new Date()));

        assertEquals(3,ratingService.getRating("cubeMaze","Marcel"));

    }

    @Test
    public void testSetRatingWithNoMatches() {
        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));
        ratingService.setRating(new Rating("Viktoria","cubeMaze",4,new Date()));

        assertEquals(0,ratingService.getRating("tiles","Marcel"));
        assertEquals(0,ratingService.getRating("cubeMaze","Erik"));

    }

    @Test
    public void testSetRating() {
        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));
        ratingService.setRating(new Rating("Viktoria","cubeMaze",4,new Date()));

        assertEquals(2,ratingService.getRating("cubeMaze","Marcel"));
        assertEquals(4,ratingService.getRating("cubeMaze","Viktoria"));
    }

    @Test
    public void testAverageRatingWithMultipleRatings() {

        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));
        ratingService.setRating(new Rating("Solo","cubeMaze",1,new Date()));
        ratingService.setRating(new Rating("Marian","cubeMaze",4,new Date()));
        ratingService.setRating(new Rating("Viktoria","cubeMaze",5,new Date()));

        assertEquals(3, ratingService.getAverageRating("cubeMaze"));

    }
    @Test
    public void testAverageRatingWithSingleRating() {

        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));

        assertEquals(2, ratingService.getAverageRating("cubeMaze"));

    }

    @Test
    public void testAverageRatingWithNoRatings() {

        ratingService.reset();

        assertEquals(0, ratingService.getAverageRating("cubeMaze"));

    }

    @Test
    public void testAverageRatingWithWrongGameName() {

        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));

        assertEquals(0, ratingService.getAverageRating("florida"));

    }

    @Test
    public void testGetRating() {
        ratingService.reset();

        ratingService.setRating(new Rating("Marcel","cubeMaze",2,new Date()));

        assertEquals(2, ratingService.getRating("cubeMaze","Marcel"));

    }
}
