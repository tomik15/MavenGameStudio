package sk.tuke.gamestudio.service;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.junit.jupiter.api.Test;

import sk.tuke.gamestudio.entity.Rating;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceTest {
    private final RatingService ratingService=new RatingServiceJdbc();

    @Test
    public void testRatingreset(){
        ratingService.setRating(new Rating("mines","tomas",4,new Date()));
        ratingService.reset();
        //  assertEquals(0,commentService.getComments("mines").size());
        assertEquals(0,ratingService.getRating("mines","tomas"));
    }

    @Test
    public void testsetRating(){

    }
}
