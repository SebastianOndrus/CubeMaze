package org.gamestudio.server.controller;


import org.gamestudio.game.core.Colours;
import org.gamestudio.game.core.Cube;
import org.gamestudio.game.core.GameField;
import org.gamestudio.game.core.tiles.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/cubeMaze")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class cubeMazeController {
    private GameField gameField = new GameField(3);


    @RequestMapping
    public String cubeMaze(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column) {
        if ( row != null && column != null) {
            calculateMove(row,column);
        }
        return "cubeMaze";
    }

    public String getHtmlField() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>\n");

        for ( int row = 0; row < gameField.getFieldHeight(); row++) {
            stringBuilder.append("<tr>\n");
            for (int column = 0; column < gameField.getFieldWidth(); column++) {
                var tile = gameField.getTile(column,row);
                stringBuilder.append("<td>\n");
                stringBuilder.append("<a href='/cubeMaze?row=" + row + "&column=" + column +"'>\n");
                if (gameField.getCube().getCubeX() == column && gameField.getCube().getCubeY() == row ) {
                    stringBuilder.append("<img src='images/tiles/cubeTile.png'>");
                } else {
                    stringBuilder.append("<img src='images/tiles/" + getImageName(tile) + ".png'>");
                }
                stringBuilder.append("</a>\n");
                stringBuilder.append("</td>\n");
            }
            stringBuilder.append("</tr>\n");
        }
        stringBuilder.append("<table>\n");

        return stringBuilder.toString();
    }

    private String getImageName(Tile tile) {
        if ( tile instanceof Finish) {
            return "finishTile";
        }else if ( tile instanceof Road) {
            return "roadTile";
        }else if ( tile instanceof Marked) {
            if (((Marked) tile).getMarkedColour() == Colours.BLUE) {
                return "blueTile";
            } else if (((Marked) tile).getMarkedColour() == Colours.RED) {
                return "redTile";
            }
        } else if ( tile instanceof Marker ) {
            if (((Marker) tile).getMarkerColour() == Colours.RED) {
                return "markerRedTile";
            } else if (((Marker) tile).getMarkerColour() == Colours.BLUE) {
                return "markerBlueTile";
            } else {
                return "markerEmptyTile";
            }
        }
        return "emptyTile";
    }

    private void calculateMove(int row, int column) {
        Cube cube = gameField.getCube();
        if ( cube.getCubeX() - column == 1 && cube.getCubeY() - row == 0) { // pohyb dolava
            gameField.MoveCube("L");
        } else if (cube.getCubeX() - column == -1 && cube.getCubeY() - row == 0) { // pohyb doprava
            gameField.MoveCube("R");
        } else if (cube.getCubeY() - row == -1 && cube.getCubeX() - column == 0) { // pohyb dole
            gameField.MoveCube("D");
        } else if (cube.getCubeY() - row == 1 && cube.getCubeX() - column == 0) { // pohyb hore
            gameField.MoveCube("U");
        }
    }
}
