package sk.tuke.gamestudio.game;

import sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tiles.core.Field;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private enum Option {
        TILES, MINES, LIGHTSOUT, EXIT
    }
    private Scanner scanner = new Scanner(System.in);




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
        var field=new sk.tuke.gamestudio.game.lightsOut.core.Field(3,3);
        var ui=new sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI(field);
        var score=new Score();
        var timestampt=ui.play();
        String databaseName="LightsOut";
        score.databaseConnectionSave(timestampt,databaseName);
        score.ConnectLoad(databaseName);

    }

    private void playMines() throws SQLException {
        var field = new sk.tuke.gamestudio.game.mines.core.Field(4, 4, 2);
        var ui = new sk.tuke.gamestudio.game.mines.consoleui.ConsoleUI(field);
        var score = new Score();
        var timestampt = ui.play();
        String databaseName = "Mines";
        score.databaseConnectionSave(timestampt, databaseName);
        score.ConnectLoad(databaseName);
    }

    private void playTiles() throws SQLException {
        var field=new Field(2,2);
        var ui=new ConsoleUI(field);
        var score=new Score();
        var timestampt=ui.play();
        String databaseName="Tiles";
        score.databaseConnectionSave(timestampt,databaseName);
        score.ConnectLoad(databaseName);

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




}
