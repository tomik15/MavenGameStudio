package sk.tuke.gamestudio.entity;

import java.util.Date;

public class Rating {

    private String game;
    private String username;
    private int rating;
    private Date rated_at;


    public Rating(String game, String username, int rating, Date rated_at) {
        this.game = game;
        this.username = username;
        this.rating = rating;
        this.rated_at = rated_at;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRated_at() {
        return rated_at;
    }

    public void setRated_at(Date rated_at) {
        this.rated_at = rated_at;
    }
}
