package org.gamestudio.game.core.tiles;

public class Empty extends Tile{

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public Empty() {

    }

    @Override
    public void printTile() {
        System.out.print(ANSI_BLACK + "▢" + ANSI_RESET);
    }
}
