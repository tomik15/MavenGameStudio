package sk.tuke.gamestudio.game.tiles.core;

import sk.tuke.gamestudio.game.mines.core.FieldState;

import java.util.Random;

public class Field {
    private final int rowCount;

    private final int columnCount;

    private final Tile[][] tiles;

    private int emptyTileRow;

    private int emptyTileColumn;
    private int score=0;

    private long startTime=0;

    public Field(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
        startTime=System.currentTimeMillis();
    }

    private void generate() {
        setFieldToInitialState();
        shuffle();
    }

    private void setFieldToInitialState() {
        var tile = 1;
        for (var row = 0; row < rowCount; row++) {
            for (var column = 0; column < columnCount; column++) {
                tiles[row][column] = new Tile(tile);
                tile++;
            }
        }

        emptyTileRow = rowCount - 1;
        emptyTileColumn = columnCount - 1;
        tiles[emptyTileRow][emptyTileColumn] = null;
    }

    private void shuffle() {
        var random = new Random();
        for (var move = 1; move < 1000 * rowCount * columnCount; move++) {
            move(random.nextInt(rowCount * columnCount - 1) + 1);
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    public void move(int tileNumber) {
        if (emptyTileRow > 0 && tiles[emptyTileRow - 1][emptyTileColumn].getValue() == tileNumber) {
            exchangeWithEmptyTile(emptyTileRow - 1, emptyTileColumn);
        } else if (emptyTileRow + 1 < rowCount && tiles[emptyTileRow + 1][emptyTileColumn].getValue() == tileNumber) {
            exchangeWithEmptyTile(emptyTileRow + 1, emptyTileColumn);
        } else if (emptyTileColumn > 0 && tiles[emptyTileRow][emptyTileColumn - 1].getValue() == tileNumber) {
            exchangeWithEmptyTile(emptyTileRow, emptyTileColumn - 1);
        } else if (emptyTileColumn + 1 < columnCount && tiles[emptyTileRow][emptyTileColumn + 1].getValue() == tileNumber) {
            exchangeWithEmptyTile(emptyTileRow, emptyTileColumn + 1);
        }
    }

    private void exchangeWithEmptyTile(int row, int column) {
        tiles[emptyTileRow][emptyTileColumn] = tiles[row][column];
        tiles[row][column] = null;
        emptyTileRow = row;
        emptyTileColumn = column;
    }

    public boolean isSolved() {
        var tile = 1;
        for (var row = 0; row < rowCount; row++) {
            for (var column = 0; column < columnCount; column++) {
                if (tiles[row][column] != null && tiles[row][column].getValue() != tile)
                    return false;
                tile++;
            }
        }
     //  score=computeScore();

        return true;
    }

    public int computeScore(){
        if(isSolved()){
            final long rawScore=rowCount*columnCount*40-(
                    (System.currentTimeMillis()-startTime)/1000);
         //   System.out.println("rawscore " +rawScore);

            if (rawScore<=0){
                return 0;
            }
            else {
                return (int) rawScore;
            }
        }
        else return 0;

    }

    public int getScore(){
        return score;
    }
}
