package sk.tuke.gamestudio.service;
import sk.tuke.gamestudio.entity.ScoreNew;

import java.util.List;

public interface ScoreService {

    /*
    *adds a new score record to database
     @param score new record
    *
    *
     */
    void addScore(ScoreNew score);

    List<ScoreNew> getBestScore(String game);

    void reset();
}
