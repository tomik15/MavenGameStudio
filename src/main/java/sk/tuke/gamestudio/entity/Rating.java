package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.util.Date;


//CREATE TABLE rating (
//        game VARCHAR(64) NOT NULL,
//        username VARCHAR(64) NOT NULL,
//        rating INTEGER NOT NULL CHECK(rating BETWEEN 1 and 5),
//        rated_at TIMESTAMP NOT NULL,
//        CONSTRAINT game_username UNIQUE (game,username)
//
//        );
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name ="GameUsername",columnNames = {"game","username"})})
public class Rating {

    @Id
    @GeneratedValue
    private int ident;
    @Column(nullable = false, length=64)
    private String game;
    @Column(nullable = false, length=64)
    private String username;
    @Column(columnDefinition = "INT CHECK(rating BETWEEN 1 AND 5) NOT NULL")
    private int rating;



    @Column(nullable = false)
    private Date rated_at;

    public Rating(){

    }


    public Rating(String game, String username, int rating, Date rated_at) {
        this.game = game;
        this.username = username;
        this.rating = rating;
        this.rated_at = rated_at;
    }
    public int getIdent() {
        return ident;
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
