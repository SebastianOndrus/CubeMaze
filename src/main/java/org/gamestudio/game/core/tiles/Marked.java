package org.gamestudio.game.core.tiles;

import org.gamestudio.game.core.Colours;

public class Marked extends Tile implements Walkable {

    private final Colours markedColour;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Marked( Colours colour ) {
        this.markedColour = colour;
    }


    public Colours getMarkedColour() {
        return markedColour;
    }

    @Override
    public void printTile() {
        if ( markedColour == Colours.BLUE) {
            System.out.print( ANSI_BLUE + "▢" + ANSI_RESET);
        } else if ( markedColour == Colours.RED ) {
            System.out.print( ANSI_RED + "▢" + ANSI_RESET);
        } else {
            System.out.print("▢");
        }

    }
}
