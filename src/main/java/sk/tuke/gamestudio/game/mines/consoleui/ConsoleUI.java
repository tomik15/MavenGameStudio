package sk.tuke.gamestudio.game.mines.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.game.mines.core.Clue;
import sk.tuke.gamestudio.game.mines.core.Field;
import sk.tuke.gamestudio.game.mines.core.FieldState;
import sk.tuke.gamestudio.game.mines.core.Tile;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJdbc;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private Field field;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private RatingService ratingService;


//    @Autowired
//    private RatingService ratingService=new RatingServiceJdbc();

    public ConsoleUI(Field field) {
        this.field = field;
    }

//    private void printHelpMessage() {
//
//        System.out.println("Hello, This is help message for how play the game (mines)" + "\n"
//                + "X mark the spot for the blank space" + "\n"
//                + "you are moving X spot always in direction up,down,right and left" + "\n"
//                + "movement of X(blank space) is done via console input with M/m and then coordinates for example:ma2 " + "\n"
//                + "coordinates are give in order of first Letter(row) then numbers(colum) example: mb4" + "\n"
//                + "X/x as input will close the application");
//    }
    public int play() throws SQLException {
//        Timestamp startTimestamp=new Timestamp(System.currentTimeMillis());
//        Instant start=Instant.now();
        do {
            printField();
            processInput();
        } while (field.getState() == FieldState.PLAYING);
        if(field.getState()!= FieldState.SOLVED){
            printField();
            System.out.println("Set username");
            var username=scanner.nextLine();
            System.out.println("Set rating for game");
            int rating1= Integer.parseInt(scanner.nextLine());

            Rating rating=new Rating("mines",username, rating1,new Date());
            ratingService.setRating(rating);


        }
        printField();
        //vypis rating z databazy
        var rating = ratingService.getAverageRating("mines");
        System.out.println("Average rating of mines:"+rating);



        System.out.println(field.getState());
        int score= field.getScore();
      //  System.out.println("tuje je score"+field.getScore());
        return score;
    }

    private void processInput() {
        System.out.print("Enter input: ");
        var line = scanner.nextLine().toUpperCase().trim();
        if ("X".equals(line))
            System.exit(0);
        var pattern = Pattern.compile("([OM])([A-I])([1-9])");
        var matcher = pattern.matcher(line);
        if (matcher.matches()) {
            var row = matcher.group(2).charAt(0) - 'A';
            var column = Integer.parseInt(matcher.group(3)) - 1;
            if("O".equals(matcher.group(1)))
                field.openTile(row, column);
            else
                field.markTile(row, column);
        } else {
            System.err.println("Invalid input!");
        }
    }

    private void printField() {
        printFieldHeader();
        printFieldBody();
    }

    private void printFieldBody() {
        for (var row = 0; row < field.getRowCount(); row++) {
            System.out.print((char) ('A' + row));
            for (var column = 0; column < field.getColumnCount(); column++) {
                var tile = field.getTile(row, column);
                System.out.print(" ");
                printTile(tile);
            }
            System.out.println();
        }
    }

    private void printFieldHeader() {
        System.out.print(" ");
        for (var column = 0; column < field.getColumnCount(); column++) {
            System.out.print(" ");
            System.out.print(column + 1);
        }
        System.out.println();
    }

    private void printTile(Tile tile) {
        switch (tile.getState()) {
            case CLOSED:
                System.out.print("-");
                break;
            case MARKED:
                System.out.print("M");
                break;
            case OPEN:
                if (tile instanceof Clue)
                    System.out.print(((Clue) tile).getValue());
                else
                    System.out.print("X");
        }
    }
}
