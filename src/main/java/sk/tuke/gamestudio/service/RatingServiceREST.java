package sk.tuke.gamestudio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;

public class RatingServiceREST implements RatingService{

    private final String url="http://localhost:8080/api";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(url+"/rating",rating,Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        Rating rating= restTemplate.getForEntity(url+"/rating/"+game+"/avg",Rating.class).getBody();
       // return ((Number)rating.getRating()).intValue();
        System.out.println("afther  method");
      int numb=1;
      var avgrating=0;
      var sumOfRating=0;
        for (Rating rating1 : rating) {
            if(rating1.getGame()==game){
                sumOfRating+=rating1.getRating();
                numb++;
            }
        }
        return avgrating=(sumOfRating/numb);
    }

    @Override
    public int getRating(String game, String username) {
        Rating[] rating= restTemplate.getForEntity(url+"/rating"+game,Rating[].class).getBody();
        for (Rating rating1 : rating) {
            if(rating1.getGame()==game && rating1.getUsername()==username)
                return rating1.getRating();
        }
        return 0;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Reset not supported via web.");
    }
}
