package org.gamestudio.game.core;

import org.gamestudio.game.core.tiles.*;

import java.time.Duration;
import java.time.Instant;

@SuppressWarnings("SuspiciousNameCombination")
public class GameField {

    private int fieldWidth;
    private int fieldHeight;
    private final Levels level = new Levels();
    private Tile[][] levelMap;
    private int[][] tiles;
    private Cube cube;
    private GameState gameState = GameState.PLAYING;

    private int moveCount = 0;
    private Instant start;

    public GameField(int levelNumber ) {
        getLevelInformation(levelNumber-1);
        tiles = level.getLevelMapNumbers(levelNumber-1);
        start = Instant.now();
    }

    private void getLevelInformation(int levelNumber ) {
        this.levelMap = level.getLevelMap(levelNumber);
        this.fieldWidth = level.getLevelWidth(levelNumber);
        this.fieldHeight = level.getLevelHeight(levelNumber);
        this.cube = new Cube(level.getCubeX(levelNumber),level.getCubeY(levelNumber));

    }

    public boolean MoveCube(String uInput) {

        boolean cubeMoved = false;
        if ( !gameState.equals(GameState.PLAYING) ) {
            return false;
        }

        if ( uInput.equals("X")) {
            gameState = GameState.LOSE;
            return true;
        }
        switch (uInput) {
            case "L" -> cubeMoved = moveCubeLeft();
            case "R" -> cubeMoved = moveCubeRight();
            case "U" -> cubeMoved = moveCubeUp();
            case "D" -> cubeMoved = moveCubeDown();
            default -> {

            }
        }
        if (!cubeMoved) {
            return false;
        } else {
            moveCount++;
        }

        checkTileUnderCube();
        return true;
    }

    private void checkTileUnderCube() {
        Tile tileToCheck = levelMap[cube.getCubeY()][cube.getCubeX()];
        if ( tileToCheck instanceof Finish ) {
            gameState = GameState.WIN;
        } else if (tileToCheck instanceof Marker) {
            markCube((Marker) tileToCheck);
        }

    }

    private void markCube(Marker marker) {
        if ( marker.getMarkerColour() == Colours.RED ) {
            cube.setCubeBottom(Colours.RED);
        } else if (marker.getMarkerColour() == Colours.BLUE) {
            cube.setCubeBottom(Colours.BLUE);
        } else if (marker.getMarkerColour() == Colours.NONE) {
            cube.setCubeBottom(Colours.NONE);
        }
    }


    private boolean moveCubeLeft() {

        if ( cube.getCubeX() >= 1 ) {
            if ( getTile(cube.getCubeX() -1, cube.getCubeY()) instanceof Empty ) {
                return false;
            }

            Tile nextTile = getTile(cube.getCubeX() -1, cube.getCubeY());
            if ( !(nextTile instanceof Walkable) ) {
                return false;
            } else if ( nextTile instanceof Marked )  {
                if ( ((Marked) nextTile).getMarkedColour().compareTo(cube.getCubeLeft()) != 0 ) {
                    return false;
                }
            }

            cube.setCubeX(cube.getCubeX()-1);

            Colours tmpLeft = cube.getCubeLeft();
            Colours tmpRight = cube.getCubeRight();
            Colours tmpTop = cube.getCubeTop();
            Colours tmpBottom = cube.getCubeBottom();

            cube.setCubeLeft(tmpTop);
            cube.setCubeBottom(tmpLeft);
            cube.setCubeRight(tmpBottom);
            cube.setCubeTop(tmpRight);

            return true;
        }
        return false;

    }

    private boolean moveCubeRight() {
        if ( cube.getCubeX() < getFieldWidth()-1  ) {

            Tile nextTile = getTile(cube.getCubeX()+1, cube.getCubeY());
            if ( !(nextTile instanceof Walkable) ) {
                return false;
            } else if ( nextTile instanceof Marked )  {
                if ( ((Marked) nextTile).getMarkedColour().compareTo(cube.getCubeRight()) != 0 ) {
                    return false;
                }
            }

            cube.setCubeX(cube.getCubeX()+1);

            Colours tmpLeft = cube.getCubeLeft();
            Colours tmpRight = cube.getCubeRight();
            Colours tmpTop = cube.getCubeTop();
            Colours tmpBottom = cube.getCubeBottom();

            cube.setCubeLeft(tmpBottom);
            cube.setCubeBottom(tmpRight);
            cube.setCubeRight(tmpTop);
            cube.setCubeTop(tmpLeft);

            return true;
        }
        return false;
    }

    private boolean moveCubeUp() {
        if ( cube.getCubeY() >= 1 ) {

            Tile nextTile = getTile(cube.getCubeX(), cube.getCubeY()-1);
            if ( !(nextTile instanceof Walkable) ) {
                return false;
            } else if ( nextTile instanceof Marked )  {
                if ( ((Marked) nextTile).getMarkedColour().compareTo(cube.getCubeBack()) != 0 ) {
                    return false;
                }
            }

            cube.setCubeY(cube.getCubeY()-1);

            Colours tmpFront = cube.getCubeFront();
            Colours tmpBack = cube.getCubeBack();
            Colours tmpTop = cube.getCubeTop();
            Colours tmpBottom = cube.getCubeBottom();

            cube.setCubeFront(tmpBottom);
            cube.setCubeBottom(tmpBack);
            cube.setCubeBack(tmpTop);
            cube.setCubeTop(tmpFront);

            return true;
        }

        return false;
    }

    private boolean moveCubeDown() {
        if ( cube.getCubeY() < getFieldHeight()-1 ) {
            Tile nextTile = getTile(cube.getCubeX(), cube.getCubeY()+1);
            if ( !(nextTile instanceof Walkable) ) {
                return false;
            } else if ( nextTile instanceof Marked )  {
                if ( ((Marked) nextTile).getMarkedColour().compareTo(cube.getCubeFront()) != 0 ) {
                    return false;
                }
            }

            cube.setCubeY(cube.getCubeY()+1);

            Colours tmpFront = cube.getCubeFront();
            Colours tmpBack = cube.getCubeBack();
            Colours tmpTop = cube.getCubeTop();
            Colours tmpBottom = cube.getCubeBottom();

            cube.setCubeFront(tmpTop);
            cube.setCubeBottom(tmpFront);
            cube.setCubeBack(tmpBottom);
            cube.setCubeTop(tmpBack);

            return true;
        }

        return false;
    }

    public Tile getTile( int col, int row ) {
        return levelMap[row][col];
    }


    public void displayLevel() {

        for ( int y = 0; y < this.fieldHeight; y++) {
            for ( int x = 0; x < this.fieldWidth; x++) {
                if ( x == cube.getCubeX() && y == cube.getCubeY() ) {
                    cube.printCube();
                }
                else {
                    levelMap[y][x].printTile();
                }
            }
            System.out.println();
        }

        cube.displayCubeSides();
    }

    private long roundTimeInSeconds() {

        Instant end = Instant.now();
        return Duration.between(this.start,end).toSeconds();
    }

    public int getPoints() {
        int maxPoints = 200;
        int points = (int) (maxPoints - ( this.moveCount ) - (roundTimeInSeconds()));
        return Math.max(points, 0);
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public GameState getState() {
        return gameState;
    }

    public Cube getCube() {
        return this.cube;
    }

//    public Tile[][] getLevelMap() {
//        return levelMap;
//    }
    public int[][] getTiles(){
        return tiles;
    }

}
