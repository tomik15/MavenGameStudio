package sk.tuke.gamestudio.game.lightsOut.core;


import sk.tuke.gamestudio.game.mines.core.FieldState;

public class Field {
    private final int rowCount;

    private final int columnCount;

    private final Tile[][] tiles;

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
        for (var row = 0; row < rowCount; row++) {
            for (var column = 0; column < columnCount; column++) {
                if (Math.random() > 0.5) {
                    tiles[row][column] = new sk.tuke.gamestudio.game.lightsOut.core.Tile(true);
                } else {
                    tiles[row][column] = new sk.tuke.gamestudio.game.lightsOut.core.Tile(false);
                }
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void movement(int row, int colum) {

        if (row > 0) {
            checkMovementOfTile(row, colum);
            return;
        }
        if (colum > 0) {
            checkMovementOfTile(row, colum);
            return;
        }
        if (colum + 1 < columnCount) {
            checkMovementOfTile(row, colum);
            return;
        }
        if (row + 1 < rowCount) {
            checkMovementOfTile(row, colum);
            return;
        }
    }


    private void checkMovementOfTile(int row, int colum) {
        if (tiles[row][colum].isValue() == true) {
            Tile tileFalse = new Tile(false);
            tiles[row][colum] = tileFalse;
            extracted(row, colum, true);
            return;
        }
        if (tiles[row][colum].isValue() == false) {
            Tile tileTrue = new Tile(true);
            tiles[row][colum] = tileTrue;
            extracted(row, colum, false);
            return;
        }


    }

    private void extracted(int row, int colum, boolean tilestate) {
        Tile tile = new Tile(tilestate);
        Tile tile_diff_state=new Tile(!tilestate);
        if (row > 0) {
            if(tiles[row - 1][colum].isValue() == tilestate){
                tiles[row - 1][colum] = tile_diff_state;
            }
            else {
                tiles[row - 1][colum] = tile;
            }
        }
        if (colum > 0){
            if(tiles[row][colum - 1].isValue() == tilestate){
                tiles[row][colum - 1] = tile_diff_state;
            }
            else {
                tiles[row][colum - 1] = tile;
            }
        }

        if (colum + 1 < columnCount){
            if(tiles[row][colum + 1].isValue() ==tilestate){
                tiles[row][colum + 1] = tile_diff_state;
            }
            else {
                tiles[row][colum + 1] = tile;
            }
        }

        if (row + 1 < rowCount){
            if(tiles[row + 1][colum].isValue()==tilestate){
                tiles[row + 1][colum] = tile_diff_state;
            }
            else {
                tiles[row + 1][colum] = tile;
            }

        }

    }

    public Tile getTile(int row, int colum) {
        return tiles[row][colum];
    }

    public boolean isSolved() {
        for (var row = 0; row < rowCount; row++) {
            for (var column = 0; column < columnCount; column++) {
                if (!tiles[row][column].isValue())
                    return false;
            }
        }
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
