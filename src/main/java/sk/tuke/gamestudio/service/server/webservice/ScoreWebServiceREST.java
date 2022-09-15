package sk.tuke.gamestudio.service.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.List;
@RestController ///instancie triedy ako komponenty REST sluzby
@RequestMapping("/api/score")
public class ScoreWebServiceREST {

    @Autowired
    private ScoreService scoreService;

    //localhost:8080/api/score/
    //post
    //novy objekt je v tele spravy

    @PostMapping
    void addScore(@RequestBody Score score){
        scoreService.addScore(score);

    }
    //localhost:8080/api/score/mines (get) tak vrat zoznam najlepsie skore pre danu hru
    @GetMapping("/{game}")
    List<Score> getBestScores(@PathVariable String game){
        return scoreService.getBestScores(game);
    }

}
