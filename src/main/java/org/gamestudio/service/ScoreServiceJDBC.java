package org.gamestudio.service;

import org.gamestudio.entity.Score;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScoreServiceJDBC implements ScoreService{

    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "postgres";
    public static final String TOPSCORE_STATEMENT = "SELECT player, game, level, points, played_at FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
    public static final String INSERT_STATEMENT = "INSERT INTO score(player, game, level, points, played_at) VALUES (?,?,?,?,?)";

    @Override
    public void addScore(Score score) {
        try( var connection =DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement = connection.prepareStatement(INSERT_STATEMENT)
        ) {
            statement.setString(1,score.getPlayer());
            statement.setString(2,score.getGame());
            statement.setInt(3,score.getLevel());
            statement.setInt(4,score.getPoints());
            statement.setTimestamp(5,new Timestamp(score.getPlayedAt().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("Problem inserting score", e);
        }
    }

    @Override
    public List<Score> getTopScores(String game) {
        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.prepareStatement(TOPSCORE_STATEMENT)
        ) {
            statement.setString(1,game);
            try(var rs = statement.executeQuery()) {
                var scores = new ArrayList<Score>();
                while (rs.next()) {
                    scores.add(new Score(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getTimestamp(5)));
                }
                return scores;
            }
        } catch (SQLException e) {
            throw new ScoreException("Problem selecting score", e);
        }
    }

    @Override
    public void reset() {
        try( var connection =DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM score");
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting score", e);
        }
    }
}
