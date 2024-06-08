package org.gamestudio.service;

import org.gamestudio.entity.Score;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ScoreServiceTest {

    private ScoreService scoreService  = new ScoreServiceJDBC();

    @Test
    public void testReset() {
        scoreService.reset();
        assertEquals(0,scoreService.getTopScores("cubeMaze").size());
    }

    @Test
    public void testAddScore() {
        scoreService.reset();
        var date = new Date();
        scoreService.addScore(new Score("Marcel","cubeMaze",2,130,date));
        var scores = scoreService.getTopScores("cubeMaze");

        assertEquals(1,scores.size());
        assertEquals("cubeMaze",scores.get(0).getGame());
        assertEquals(2,scores.get(0).getLevel());
        assertEquals("Marcel",scores.get(0).getPlayer());
        assertEquals(130,scores.get(0).getPoints());
        assertEquals(date,scores.get(0).getPlayedAt());

    }

    @Test
    public void testGetTopScores() {
        scoreService.reset();
        var date = new Date();
        scoreService.addScore(new Score("Marcel","cubeMaze",2,130,date));
        scoreService.addScore(new Score("Margitka","cubeMaze",2,180,date));
        scoreService.addScore(new Score("Juliana","piano",2,130,date));
        scoreService.addScore(new Score("Petro","cubeMaze",2,220,date));

        var scores = scoreService.getTopScores("cubeMaze");
        assertEquals(3,scores.size());

        assertEquals("Petro",scores.get(0).getPlayer());
        assertEquals("cubeMaze",scores.get(0).getGame());
        assertEquals(220,scores.get(0).getPoints());
        assertEquals(2,scores.get(0).getLevel());
        assertEquals(date,scores.get(0).getPlayedAt());

        assertEquals("Margitka",scores.get(1).getPlayer());
        assertEquals("cubeMaze",scores.get(1).getGame());
        assertEquals(180,scores.get(1).getPoints());
        assertEquals(2,scores.get(1).getLevel());
        assertEquals(date,scores.get(1).getPlayedAt());

        assertEquals("Marcel",scores.get(2).getPlayer());
        assertEquals("cubeMaze",scores.get(2).getGame());
        assertEquals(130,scores.get(2).getPoints());
        assertEquals(2,scores.get(2).getLevel());
        assertEquals(date,scores.get(2).getPlayedAt());

    }
}
