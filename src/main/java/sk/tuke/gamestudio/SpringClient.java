package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating2;
import sk.tuke.gamestudio.game.Menu;
import sk.tuke.gamestudio.game.mines.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.mines.core.Field;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
    public static void main(String[] args){

       // SpringApplication.run(SpringClient.class);

        new SpringApplicationBuilder(SpringClient.class)
                .web(WebApplicationType.NONE).run(args);


    }
   // @Bean
    public CommandLineRunner runner(){
        return args -> {
            System.out.println("Hello from spring");
        };
    }

  //  @Bean
    public CommandLineRunner runnerMines(ConsoleUI consoleMines){
        return args -> {
            consoleMines.play();
        };

    }

 //   @Bean
    public CommandLineRunner runnerTiles(sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI consoleTiles){
        return args -> {
            consoleTiles.play();
        };

    }
     @Bean
    public CommandLineRunner runnerMenu(Menu menu){
        return args -> {
            menu.run();
        };
    }

 //   @Bean
    public CommandLineRunner runnerPlaygroundJPA(PlaygroundJPA console){
        return args -> console.play();
    }

    @Bean
    public PlaygroundJPA consolePlayGround(){
        return new PlaygroundJPA();
    }



    //doplnit console playground

    @Bean
    public sk.tuke.gamestudio.game.tiles.core.Field fieldTiles(){
        return new sk.tuke.gamestudio.game.tiles.core.Field(2,2);
    }

    @Bean
    public sk.tuke.gamestudio.game.lightsOut.core.Field fieldLight(){
        return new sk.tuke.gamestudio.game.lightsOut.core.Field(3,3);
    }

    @Bean
    public sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI consoleLight(){
        return new sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI(fieldLight());
    }

    @Bean
    public sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI consoleTiles(sk.tuke.gamestudio.game.tiles.core.Field field){
      //  return  new sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI(field);
        return new sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI(field);
    }


    @Bean
    public Field fieldMines(){
        return new Field(4,4,1);

    }

    @Bean
    public ConsoleUI consoleMines(Field field){
        return new ConsoleUI(field);
    }

    @Bean
    public ScoreService scoreService(){
     //  return new ScoreServiceJdbc();
        //  return new ScoreServiceJPA();
        return new ScoreServiceREST();
    }

    @Bean
    public RatingService ratingService(){
      //  return new RatingServiceJdbc();

      //  return new RatingServiceJPA();
      //  return new Rating2();
        return new RatingServiceREST();
    }

    @Bean
    public CommentService commentService(){
      //  return new CommentServiceJPA();
        return new CommentServiceREST();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
