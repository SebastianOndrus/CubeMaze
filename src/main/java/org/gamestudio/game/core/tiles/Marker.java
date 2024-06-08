package org.gamestudio.game.core.tiles;

import org.gamestudio.game.core.Colours;

public class Marker extends Tile implements Walkable{

    public Colours getMarkerColour() {
        return markerColour;
    }

    private final Colours markerColour;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Marker(Colours colour) {
        this.markerColour = colour;
    }

    @Override
    public void printTile() {
        if ( markerColour == Colours.BLUE) {
            System.out.print( ANSI_BLUE + "▣" + ANSI_RESET);
        } else if ( markerColour == Colours.RED ) {
            System.out.print( ANSI_RED + "▣" + ANSI_RESET);
        } else {
            System.out.print("▣");
        }

    }
}
