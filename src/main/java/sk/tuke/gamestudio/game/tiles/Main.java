package sk.tuke.gamestudio.game.tiles;

import sk.tuke.gamestudio.game.tiles.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tiles.core.Field;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var field = new Field(2, 3);
        var ui = new ConsoleUI(field);
        ui.play();
    }
}
