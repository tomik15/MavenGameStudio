package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.junit.jupiter.api.Test;

import sk.tuke.gamestudio.entity.Rating;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class RatingServiceTestSpring {


    //private final RatingService ratingService=new RatingServiceJdbc();
    @Autowired
    RatingService ratingService;

    @org.junit.Test
    public void testRatingReset(){
        ratingService.setRating(new Rating("mines","tomas",4,new Date()));

        ratingService.reset();
        //  assertEquals(0,commentService.getComments("mines").size());
        assertEquals(0,ratingService.getRating("mines","tomas"));
    }

    @org.junit.Test
    public void testSetRating(){
        var date=new  Date();
        ratingService.setRating(new Rating("mines","tomas",2,date));
        ratingService.setRating(new Rating("mines","tomas",4,date));
        assertEquals(4,ratingService.getRating("mines","tomas"));

    }

    @org.junit.Test
    public  void  testAverageRating(){
        var date=new  Date();
        ratingService.setRating(new Rating("mines","tom",2,date));
        ratingService.setRating(new Rating("mines","dano",4,date));
        assertEquals(3,ratingService.getAverageRating("mines"));
    }

    @org.junit.Test
    public void testGetRating(){
        var date=new  Date();
        ratingService.setRating(new Rating("mines","tom",2,date));
        ratingService.setRating(new Rating("mines","dano",4,date));

        assertEquals(2,ratingService.getRating("mines","tom"));
        assertEquals(0,ratingService.getRating("mines","fredo"));

    }
}
