package sk.tuke.gamestudio.game.lightsOut.consoleui;


import sk.tuke.gamestudio.game.lightsOut.core.Field;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private final Field field;

    private Scanner scanner=new Scanner(System.in);

    public ConsoleUI(Field field) {
        this.field = field;
    }
    private void printHelpMessage() {

        System.out.println("Hello, This is help message for how play the game (LightOut" + "\n"
                + "# mark the spot for the light turn on " + "\n"
                + ". mark the spot for the light turn off" + "\n"
                + "your goal is to turned on all the tiles on the field ");
    }

    public Timestamp play() throws SQLException {
        printHelpMessage();
        Instant start=Instant.now();
        do {

            printField();
            processInput();
        }while (!field.isSolved());
        Instant finish=Instant.now();
        long timeElapsed= Duration.between(start,finish).toSeconds();
        Timestamp timestamp=Timestamp.from(Instant.ofEpochSecond(timeElapsed));
        System.out.println("game is solved");
        System.out.println("Playing time:"+timeElapsed);
        printField();
       // extracted(timestamp);
        return timestamp;
    }



    private void processInput() {
        System.out.println("Enter location which you want to turn off");
        var line = scanner.nextLine().toUpperCase().trim();
        if ("X".equals(line)) {
            System.exit(0);
        }
        var pattern = Pattern.compile("([A-I])([1-9])");

        var matcher = pattern.matcher(line);
        if (matcher.matches()) {
            var row = matcher.group(1).charAt(0) - 'A';
            var colum = Integer.parseInt(matcher.group(2)) - 1;
                if (row > field.getRowCount() - 1 || colum > field.getColumnCount() - 1) {
                    System.out.println("invalid input");
                    return;
                }
                field.movement(row,colum);
            }

    }

    private void printField() {
        System.out.print("  ");
        for (var colum = 0; colum < field.getColumnCount(); colum++) {
            System.out.print(" ");
            System.out.print(colum + 1);
        }
        System.out.println();
        System.out.println("----------------");

        for (var row = 0; row < field.getRowCount(); row++) {
            System.out.print((char) ('A' + row));
            System.out.print("|");
            for (var column = 0; column < field.getColumnCount(); column++) {
                var tile = field.getTile(row, column);
                System.out.print(" ");
                if (tile.isValue()==true)
                    System.out.print("#");
                if(tile.isValue()==false){
                    System.out.printf(".");
                }
            }
            System.out.println("");
        }
    }



}
