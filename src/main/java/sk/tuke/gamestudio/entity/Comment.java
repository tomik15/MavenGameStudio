package sk.tuke.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int ident;



    private String game;
    private String username;
    private String comment;
    private Date commented_at;

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

    public String getCommet() {
        return comment;
    }

    public void setCommet(String comment) {
        this.comment = comment;
    }

    public Date getCommented_at() {
        return commented_at;
    }

    public void setCommented_at(Date commented_at) {
        this.commented_at = commented_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", commet='" + comment + '\'' +
                ", commented_at=" + commented_at +
                '}';
    }

    public Comment(){

    }

    public Comment(String game, String username, String comment, Date commented_at) {
        this.game = game;
        this.username = username;
        this.comment = comment;
        this.commented_at = commented_at;
    }
}
