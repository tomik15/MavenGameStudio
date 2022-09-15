package sk.tuke.gamestudio.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@EntityScan(basePackages = "sk.tuke.gamestudio.entity")
public class GameStudioServer {
    public static void main(String[] args){

        SpringApplication.run(GameStudioServer.class);

       // new SpringApplicationBuilder(GameStudioServer.class)
        //        .web(WebApplicationType.NONE).run(args);


    }

    @Bean
    public ScoreService scoreService(){
     //  return new ScoreServiceJdbc();
          return new ScoreServiceJPA();
    }

    @Bean
    public RatingService ratingService(){
      //  return new RatingServiceJdbc();
        return new RatingServiceJPA();
       // return new Rating2();
    }
    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }
}
