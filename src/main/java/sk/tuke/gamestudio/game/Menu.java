package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tiles.core.Field;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Menu {

    private enum Option {
        TILES, MINES, LIGHTSOUT, EXIT
    }

    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private ScoreService scoreService;
    //  private final ScoreService scoreService=new ScoreServiceJdbc();
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    //    private final CommentService commentService=new CommentServiceJdbc();
  //  @Autowired
    private ConsoleUI consoleUI4tiles;
  //  @Autowired
    private sk.tuke.gamestudio.game.mines.consoleui.ConsoleUI consoleUI4mines;
//    @Autowired
    private sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI consoleUI4lightsout;


    public void run() throws SQLException {
        while (true) {
            switch (showMenu()) {
                case TILES:
                    playTiles();
                    break;
                case MINES:
                    playMines();
                    break;
                case LIGHTSOUT:
                    playLightsOut();
                    break;
                case EXIT:

                    return;
            }
        }
    }

    private void playLightsOut() throws SQLException {
        var gameName = "lightsOut";
        var date = new Date();

        var field = new sk.tuke.gamestudio.game.lightsOut.core.Field(3, 3);
        consoleUI4lightsout = new sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI(field);
        consoleUI4lightsout.play();
        if (field.isSolved()) {
            var score = field.computeScore();
            System.out.println("score " + score);
            if (score != 0) {
                ScoreCommentRatingPrint(gameName, date, score);
            }
        }
    }

    private void playMines() throws SQLException {
        var gameName = "mines";
        var date = new Date();

        var field = new sk.tuke.gamestudio.game.mines.core.Field(4, 4, 1);
        consoleUI4mines = new sk.tuke.gamestudio.game.mines.consoleui.ConsoleUI(field);
        var gameScore = consoleUI4mines.play();
        //   System.out.println(gameScore);
        if (gameScore != 0) {
            ScoreCommentRatingPrint(gameName, date, gameScore);
        }
    }

    private void playTiles() throws SQLException {

        var gameName = "tiles";
        var date = new Date();

        var field = new Field(2, 2);
        consoleUI4tiles = new ConsoleUI(field);
        consoleUI4tiles.play();
        if (field.isSolved()) {
            var score2 = field.computeScore();
            // var score=field.getScore();
            System.out.println("score " + score2);
            if (score2 != 0) {
                ScoreCommentRatingPrint(gameName, date, score2);
            }
        }
    }

    private void ScoreCommentRatingPrint(String gameName, Date date, int score2) {
        System.out.println("Please enter Name");
        var username = scanner.nextLine();
        scoreService.addScore(new sk.tuke.gamestudio.entity.Score(gameName, username, score2, date));
        var list = scoreService.getBestScores(gameName);
        printList(list);
        //comment
        System.out.println("Do you want to review/give comment to the game? Y/N");
        var line = scanner.nextLine().toUpperCase().trim();
        if ("Y".equals(line)) {
            System.out.println("Please enter the review of the game:");
            var comment = scanner.nextLine();
            commentService.addComment(new Comment(gameName, username, comment, date));

        }
        var comments = commentService.getComments(gameName);
        printCommentsList(comments);

        System.out.println("Set rating for game");
        int rating1 = Integer.parseInt(scanner.nextLine());

        Rating rating = new Rating(gameName, username, rating1, new Date());
        ratingService.setRating(rating);
        //vypis rating z databazy
        var rating2 = ratingService.getAverageRating(gameName);
        System.out.println("Average rating of game:" + gameName + " " + rating2);
    }

    private Menu.Option showMenu() {
        System.out.println("Hello " + System.getProperty("user.name"));
        System.out.println("Menu.");
        for (var option : Menu.Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");

        var selection = -1;
        do {
            System.out.println("Option: ");
            try {
                selection = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                selection = -1;
            }
        } while (selection <= 0 || selection > Menu.Option.values().length);

        return Menu.Option.values()[selection - 1];
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void printList(List<sk.tuke.gamestudio.entity.Score> list) {
        for (sk.tuke.gamestudio.entity.Score score : list) {
            System.out.println(score.getGame() + " " + score.getUsername() + " " + score.getPoints() + " " + score.getPlayedAt());
        }

    }

    private void printCommentsList(List<Comment> list) {
        for (Comment comment : list) {
            System.out.println(comment.getGame() + " " + comment.getUsername() + " " + comment.getCommet() + " " + comment.getCommented_at());
        }

    }


}
