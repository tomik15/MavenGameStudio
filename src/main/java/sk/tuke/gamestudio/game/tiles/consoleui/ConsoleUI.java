package sk.tuke.gamestudio.game.tiles.consoleui;

import sk.tuke.gamestudio.game.tiles.core.Field;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class ConsoleUI {
    private final Field field;

    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(Field field) {
        this.field = field;
    }

    private void printHelpMessage() {

        System.out.println("Hello, This is help message for how play the game (n puzzle)" + "\n"
                + "- mark the spot for the blank space" + "\n"
                + "you are moving - spot always in direction up,down,right and left" + "\n"
                + "movement of -(blank space) is done via console input with tile number to be moved " + "\n"
                + "");
    }

    public int play() throws SQLException {
        printHelpMessage();
     //   Instant start=Instant.now();
        long timeStart=System.currentTimeMillis();
        do {
            printField();
            processInput();
        } while (!field.isSolved());
//        Instant finish=Instant.now();
//        long timeFinish=System.currentTimeMillis();
//        long timeElapsed= Duration.between(start,finish).toSeconds();
//        long timeElapsed2=timeFinish-timeStart;
//
//        Timestamp timestamp=Timestamp.from(Instant.ofEpochSecond(timeElapsed));
        printField();
        int score= field.getScore();
        //  System.out.println("tuje je score"+field.getScore());
        return score;

//        System.out.println("Solved");
//
//        return timestamp;
    }

    private void printField() {
        for (var row = 0; row < field.getRowCount(); row++) {
            for (var column = 0; column < field.getColumnCount(); column++) {
                var tile = field.getTile(row, column);
                System.out.print(" ");
                if (tile == null)
                    System.out.print("-");
                else
                    System.out.printf("%2d", tile.getValue());
            }
            System.out.println();
        }
    }

    private void processInput() {
        System.out.println("Enter tile number to move: ");
        var line = scanner.nextLine().toUpperCase().trim();
        if ("X".equals(line))
            System.exit(0);
        try {
            var tile = Integer.parseInt(line);
            field.move(tile);
        } catch (NumberFormatException e) {
            System.err.println("Invalid tile!");
        }
    }
}
