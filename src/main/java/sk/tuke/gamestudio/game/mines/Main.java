package sk.tuke.gamestudio.game.mines;

import sk.tuke.gamestudio.game.mines.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.mines.core.Field;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var field = new Field(9, 9, 10);
        var ui = new ConsoleUI(field);
        ui.play();
    }
}
