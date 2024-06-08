package org.gamestudio.game.core.tiles;

public class Finish extends Tile implements Walkable{

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public Finish() {

    }
    @Override
    public void printTile() {
        System.out.print(ANSI_YELLOW + "▣" + ANSI_RESET);
    }
}
