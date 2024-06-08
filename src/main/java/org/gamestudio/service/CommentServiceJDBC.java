package org.gamestudio.service;

import org.gamestudio.entity.Comment;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService{

    public static final String JDBC_URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "postgres";

    public static final String SELECT_STATEMENT = "SELECT player, game, comment_text, commented_at FROM comment WHERE game = ?";
    public static final String INSERT_STATEMENT = "INSERT INTO comment(player, game, comment_text, commented_at) VALUES (?,?,?,?)";


    @Override
    public void addComment(Comment comment) throws CommentException{
        try(var connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            var statement = connection.prepareStatement(INSERT_STATEMENT)
        ) {
            statement.setString(1, comment.getPlayer());
            statement.setString(2,comment.getGame());
            statement.setString(3,comment.getComment());
            statement.setTimestamp(4,new Timestamp(comment.getCommentedAt().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommentException("Problem inserting comment",e);
        }
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.prepareStatement(SELECT_STATEMENT)
        ) {
            statement.setString(1,game);
            try(var rs = statement.executeQuery()) {
                var comments = new ArrayList<Comment>();
                while (rs.next()) {
                    comments.add(new Comment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4)));
                }
                return comments;
            }
        } catch (SQLException e) {
            throw new CommentException("Problem getting comments",e);
        }
    }

    @Override
    public void reset() throws CommentException {
        try( var connection =DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM comment");
        } catch (SQLException e) {
            throw new CommentException("Problem resetting comments",e);
        }
    }
}
