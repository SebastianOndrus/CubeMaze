package org.gamestudio;

import org.gamestudio.entity.Score;
import org.gamestudio.service.ScoreService;
import org.gamestudio.service.ScoreServiceJDBC;

import java.sql.DriverManager;
import java.util.Date;

public class TestJDBC {
    public static void main(String[] args) throws Exception {

        ScoreService service = new ScoreServiceJDBC();
        service.reset();
        service.addScore(new Score("Milan","cubeMaze",2,118,new Date()));
        service.addScore(new Score("SDVn","cubeMaze",3,151,new Date()));
        service.addScore(new Score("Petko","cubeMaze",2,1134,new Date()));
        service.addScore(new Score("Mdsan","cubeMaze",1,112,new Date()));

        var scores = service.getTopScores("cubeMaze");
        System.out.println(scores);
    }
}
