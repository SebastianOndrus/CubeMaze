package org.gamestudio.game.core;


import org.junit.Test;
import static org.junit.Assert.*;

public class GameFieldCubeTest {


    @Test
    public void testMoveCubeLeftPossible() {
        GameField gameField = new GameField(1);

        assertTrue(gameField.MoveCube("L"));

    }

    @Test
    public void testMoveCubeLeftNotPossible() {
        GameField gameField = new GameField(2);

        assertFalse(gameField.MoveCube("L"));

    }

    @Test
    public void testMoveCubeRightPossible() {
        GameField gameField = new GameField(4);

        assertTrue(gameField.MoveCube("R"));

    }

    @Test
    public void testMoveCubeRightNotPossible() {
        GameField gameField = new GameField(2);

        assertFalse(gameField.MoveCube("R"));

    }

    @Test
    public void testMoveCubeDownPossible() {
        GameField gameField = new GameField(2);

        assertTrue(gameField.MoveCube("D"));

    }

    @Test
    public void testMoveCubeDownNotPossible() {
        GameField gameField = new GameField(1);

        assertFalse(gameField.MoveCube("D"));

    }

    @Test
    public void testMoveCubeUpPossible() {
        GameField gameField = new GameField(2);

        gameField.MoveCube("D");

        assertTrue(gameField.MoveCube("U"));

    }

    @Test
    public void testMoveCubeUpNotPossible() {
        GameField gameField = new GameField(2);

        assertFalse(gameField.MoveCube("U"));

    }


}
