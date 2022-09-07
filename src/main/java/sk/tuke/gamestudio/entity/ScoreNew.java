package sk.tuke.gamestudio.entity;

import java.util.Date;

public class ScoreNew {
   private String game;
   private String username;
   private int points;

    public ScoreNew(){

    }

    public ScoreNew(String game, String username, int points, Date played_At) {
        this.game = game;
        this.username = username;
        this.points = points;
        this.played_At = played_At;
    }

    @Override
    public String toString() {
        return "ScoreNew{" +
                "game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", points=" + points +
                ", played_At=" + played_At +
                '}';
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayed_At() {
        return played_At;
    }

    public void setPlayed_At(Date played_At) {
        this.played_At = played_At;
    }



    private Date played_At;


}
