package sk.tuke.gamestudio.service.server.webservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Date;

@RestController ///instancie triedy ako komponenty REST sluzby
@RequestMapping("/api/rating")
public class RatingWebServiceREST {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    void setRating(@RequestBody Rating rating){
        ratingService.setRating(rating);
    }

    @GetMapping("/{game}/{username}")
    int getRating(@PathVariable String game,@PathVariable String username){

        //   return    ratingService.getRating(game,username);
        return    ratingService.getRating(game,username);
    }

    



//
//    @GetMapping("/{game}/{username}")
//    int getRating(@PathVariable String game,@PathVariable String username){
//
//        //   return    ratingService.getRating(game,username);
//        return    ratingService.getRating(game,username);
//    }

    //pred tym vratilo int
    @GetMapping("/{game}")
    int  getAverageRating(@PathVariable String game){//skusit zmenit rating service jpa aby na getaverage vratilo rating
    //    System.out.println("rating service "+ratingService.getAverageRating(game));
      //  Rating ratingWithAvg=new Rating(game,"average",ratingService.getAverageRating(game),new Date());
       return    ratingService.getAverageRating(game);
      //  return ratingWithAvg;
    }

}
