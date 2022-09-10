package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tuke.gamestudio.SpringClient;
import sk.tuke.gamestudio.entity.Score;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class ScoreServiceTestSpring {
  //  private final ScoreService scoreService=new ScoreServiceJdbc();
   // private final ScoreService scoreService=new ScoreServiceFile();
    @Autowired
    ScoreService scoreService;

    @org.junit.Test
    public void testReset(){
        scoreService.addScore(new Score("mines","tomas",123,new Date()));

        boolean atLeast=false;
        if(scoreService.getBestScores("mines").size()>0){
            atLeast=true;
        }
        assertTrue(atLeast);
      //  assertNotEquals(0,scoreService.getBestScores("mines").size());
        scoreService.reset();
        assertEquals(0,scoreService.getBestScores("mines").size());

    }
    @org.junit.Test
    public void addScore(){
        scoreService.reset();
        var date=new Date();
        final String gameName="mines";

       scoreService.addScore(new Score(gameName,"Jaro",123,date));
        var scores=scoreService.getBestScores(gameName);
        assertEquals(1,scores.size());
        assertEquals(gameName,scores.get(0).getGame());
        assertEquals("Jaro",scores.get(0).getUsername());
        assertEquals(123,scores.get(0).getPoints());
        assertEquals(date,scores.get(0).getPlayedAt());
    }
    @org.junit.Test
    public  void BestScores(){
        var date=new Date();
        scoreService.reset();
        scoreService.addScore(new Score("mines","robert",120,date));
        scoreService.addScore(new Score("mines","tomas",3,date));
        scoreService.addScore(new Score("mines","jozef",53,date));
        scoreService.addScore(new Score("mines","dano",60,date));
        scoreService.addScore(new Score("mines","janka",70,date));
        scoreService.addScore(new Score("mines","peter",100,date));
        scoreService.addScore(new Score("mines","frantisek",1220,date));

        var score=scoreService.getBestScores("mines");
//        for (Score score1 : score) {
//            System.out.println(score1);
//        }
        assertEquals(5,score.size());
        assertEquals(5,scoreService.getBestScores("mines").size());

        assertEquals("mines",score.get(0).getGame());
        assertEquals("frantisek",score.get(0).getUsername());
        assertEquals(1220,score.get(0).getPoints());
        assertEquals(date,score.get(0).getPlayedAt());

        assertEquals("mines",score.get(1).getGame());
        assertEquals("robert",score.get(1).getUsername());
        assertEquals(120,score.get(1).getPoints());
        assertEquals(date,score.get(1).getPlayedAt());

        assertEquals("mines",score.get(2).getGame());
        assertEquals("peter",score.get(2).getUsername());
        assertEquals(100,score.get(2).getPoints());
        assertEquals(date,score.get(2).getPlayedAt());

        assertEquals("mines",score.get(3).getGame());
        assertEquals("janka",score.get(3).getUsername());
        assertEquals(70,score.get(3).getPoints());
        assertEquals(date,score.get(3).getPlayedAt());

        assertEquals("mines",score.get(4).getGame());
        assertEquals("dano",score.get(4).getUsername());
        assertEquals(60,score.get(4).getPoints());
        assertEquals(date,score.get(4).getPlayedAt());



        List<Score> list=scoreService.getBestScores("mines");
   }


}
