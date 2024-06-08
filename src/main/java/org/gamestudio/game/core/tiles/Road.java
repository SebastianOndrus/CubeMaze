package org.gamestudio.game.core.tiles;

public class Road extends Tile implements Walkable{

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public Road(){

    }
    @Override
    public void printTile() {
        System.out.print(ANSI_WHITE+"▢"+ANSI_RESET);
    }
}
