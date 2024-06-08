package org.gamestudio.game.core;

import org.gamestudio.game.core.tiles.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LevelsTest {

    private Levels levels = new Levels();


    @Test
    public void testGetLevelWidth() {

        int width1 = levels.getLevelWidth(0);
        int width2 = levels.getLevelWidth(1);
        int width3 = levels.getLevelWidth(2);
        int width4 = levels.getLevelWidth(3);
        int width5 = levels.getLevelWidth(4);
        int width6 = levels.getLevelWidth(5);
        int width7 = levels.getLevelWidth(6);
        int width8 = levels.getLevelWidth(7);

        assertEquals(7,width1);
        assertEquals(6,width2);
        assertEquals(7,width3);
        assertEquals(11,width4);
        assertEquals(5,width5);
        assertEquals(11,width6);
        assertEquals(10,width7);
        assertEquals(7,width8);

    }

    @Test
    public void testGetLevelHeight() {

        int height1 = levels.getLevelHeight(0);
        int height2 = levels.getLevelHeight(1);
        int height3 = levels.getLevelHeight(2);
        int height4 = levels.getLevelHeight(3);
        int height5 = levels.getLevelHeight(4);
        int height6 = levels.getLevelHeight(5);
        int height7 = levels.getLevelHeight(6);
        int height8 = levels.getLevelHeight(7);

        assertEquals(7,height1);
        assertEquals(9,height2);
        assertEquals(9,height3);
        assertEquals(3,height4);
        assertEquals(8,height5);
        assertEquals(3,height6);
        assertEquals(5,height7);
        assertEquals(10,height8);

    }

    @Test
    public void testGetLevelMap() {
        Tile[][] tiles = levels.getLevelMap(1);
        Tile tileRoad = tiles[0][2];
        Tile tileEmpty = tiles[0][0];
        Tile tileFinish = tiles[8][0];
        Tile tileMarker = tiles[2][1];
        Tile tileMarked = tiles[6][0];

        assertTrue(tileRoad instanceof Road);
        assertTrue(tileEmpty instanceof Empty);
        assertTrue(tileFinish instanceof Finish);
        assertTrue(tileMarker instanceof Marker);
        assertTrue(tileMarked instanceof Marked);

    }

}
