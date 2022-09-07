package sk.tuke.gamestudio.game.lightsOut;

import sk.tuke.gamestudio.game.lightsOut.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.lightsOut.core.Field;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var field = new Field(5,5);
       // System.out.println(field);
        var ui = new ConsoleUI(field);
        ui.play();
    }
}
