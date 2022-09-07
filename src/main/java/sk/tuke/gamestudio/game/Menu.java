package sk.tuke.gamestudio.game;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tiles.core.Field;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.CommentServiceJdbc;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJdbc;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private enum Option {
        TILES, MINES, LIGHTSOUT, EXIT
    }
    private Scanner scanner = new Scanner(System.in);

    private final ScoreService scoreService=new ScoreServiceJdbc();

    private final CommentService commentService=new CommentServiceJdbc();


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
        var gameName="lightsOut";
        var date=new Date();

        var field=new sk.tuke.gamestudio.game.lightsOut.core.Field(3,3);
        var ui=new sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI(field);
        ui.play();
        if(field.isSolved()){
            var score=field.computeScore();
            if(score!=0){
                System.out.println("Please enter Name");
                var username=scanner.nextLine();
                scoreService.addScore(new sk.tuke.gamestudio.entity.Score(gameName,username,score,date));
                var list=scoreService.getBestScores("lightsOut");
                printList(list);
                //comment
                System.out.println("Please enter the review of the game:");
                var comment=scanner.nextLine();
                commentService.addComment(new Comment(gameName,username,comment,date));
                var comments=commentService.getComments("lightsOut");
                printCommentsList(comments);


            }
        }
    }

    private void playMines() throws SQLException {
        var gameName="mines";
        var date=new Date();

        var field = new sk.tuke.gamestudio.game.mines.core.Field(4, 4, 1);
        var ui = new sk.tuke.gamestudio.game.mines.consoleui.ConsoleUI(field);
        var gameScore = ui.play();
     //   System.out.println(gameScore);
        if(gameScore!=0){
            System.out.println("Please enter Name");
            var username=scanner.nextLine();
            scoreService.addScore(new sk.tuke.gamestudio.entity.Score(gameName,username,gameScore,date));
            var list=scoreService.getBestScores("mines");
            printList(list);
            //comment
            System.out.println("Please enter the review of the game:");
            var comment=scanner.nextLine();
            commentService.addComment(new Comment(gameName,username,comment,date));
            var comments=commentService.getComments("mines");
            printCommentsList(comments);
        }
    }

    private void playTiles() throws SQLException {

        var gameName="tiles";
        var date=new Date();

        var field=new Field(2,2);
        var ui=new ConsoleUI(field);
        ui.play();
        if(field.isSolved()){
            var score2=field.computeScore();
           // var score=field.getScore();
            if(score2!=0){
                System.out.println("Please enter Name");
                var username=scanner.nextLine();
                scoreService.addScore(new sk.tuke.gamestudio.entity.Score(gameName,username,score2,date));
                var list=scoreService.getBestScores("tiles");
                printList(list);
                //comment
                System.out.println("Please enter the review of the game:");
                var comment=scanner.nextLine();
                commentService.addComment(new Comment(gameName,username,comment,date));
                var comments=commentService.getComments("tiles");
                printCommentsList(comments);
            }
        }
    }

    private Menu.Option showMenu() {
        System.out.println("Hello "+System.getProperty("user.name"));
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

    private void printList(List<sk.tuke.gamestudio.entity.Score> list){
        for (sk.tuke.gamestudio.entity.Score score : list) {
            System.out.println(score.getGame()+" " +score.getUsername()+" "+score.getPoints()+" "+score.getPlayedAt());
        }

    }

    private void printCommentsList(List<Comment> list){
        for (Comment comment: list) {
            System.out.println(comment.getGame()+" " +comment.getUsername()+" "+comment.getCommet()+" "+comment.getCommented_at());
        }

    }



}
