package org.gamestudio.service;

import org.gamestudio.entity.Rating;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;


public class RatingServiceJDBC implements RatingService {

    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "postgres";
    public static final String INSERT_STATEMENT = "INSERT INTO rating(player, game, rating, rated_on) VALUES (?,?,?,?) ON CONFLICT(player) WHERE game = ? DO UPDATE SET rating = ?, rated_on = ?";
    public static final String SELECT_STATEMENT_AVERAGE = "SELECT rating FROM rating WHERE game = ?";
    public static final String SELECT_STATEMENT_BY_PLAYER = "SELECT rating FROM rating WHERE game = ? AND player = ?";



    @Override
    public void setRating(Rating rating) throws RatingException {

        try(var connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            var statement = connection.prepareStatement(INSERT_STATEMENT)
        ) {
            statement.setString(1, rating.getPlayer());
            statement.setString(2,rating.getGame());
            statement.setInt(3,rating.getRating());
            statement.setTimestamp(4,new Timestamp(rating.getRatedOn().getTime()));
            statement.setString(5,rating.getGame());
            statement.setInt(6,rating.getRating());
            statement.setTimestamp(7,new Timestamp(rating.getRatedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RatingException("Problem setting rating",e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {

        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.prepareStatement(SELECT_STATEMENT_AVERAGE)
        ) {
            statement.setString(1,game);
            try(var rs = statement.executeQuery()) {
                int sum_rating = 0;
                int countOf_ratings = 0;
                while (rs.next()) {
                    countOf_ratings++;
                    sum_rating = sum_rating + rs.getInt(1);
                }
                if ( sum_rating == 0 || countOf_ratings == 0 ) {
                    return 0;
                }
                return sum_rating/countOf_ratings;
            }
        } catch (SQLException e) {
            throw new RatingException("Problem getting average rating",e);
        }

    }

    @Override
    public int getRating(String game, String player) throws RatingException {

        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.prepareStatement(SELECT_STATEMENT_BY_PLAYER)
        ) {
            statement.setString(1,game);
            statement.setString(2,player);
            try(var rs = statement.executeQuery()) {
                int result = 0;
                while (rs.next()) {
                    result = rs.getInt(1);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new RatingException("Problem getting player rating",e);
        }
    }

    @Override
    public void reset() throws RatingException {
        try(var connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            var statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM rating");
        } catch (SQLException e) {
            throw new RatingException("Problem resetting rating",e);
        }
    }
}
