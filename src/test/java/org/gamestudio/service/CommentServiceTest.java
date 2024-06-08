package org.gamestudio.service;

import org.gamestudio.entity.Comment;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CommentServiceTest {

    private final CommentService commentService = new CommentServiceJDBC();

    @Test
    public void testReset() {
        commentService.reset();

        assertEquals(0,commentService.getComments("cubeMaze").size());

    }

    @Test
    public void testAddComment() {
        commentService.reset();
        var date = new Date();
        commentService.addComment(new Comment("Marcel","cubeMaze","Testujem komentare lebo ma to bavi", date));
        var comments = commentService.getComments("cubeMaze");

        assertEquals(1,comments.size());
        assertEquals("Marcel",comments.get(0).getPlayer());
        assertEquals("cubeMaze",comments.get(0).getGame());
        assertEquals("Testujem komentare lebo ma to bavi",comments.get(0).getComment());
        assertEquals(date,comments.get(0).getCommentedAt());
    }

    @Test
    public void testGetComments() {
        commentService.reset();
        var date1 = new Date();
        var date2 = new Date();
        var date3 = new Date();
        var date4 = new Date();

        commentService.addComment(new Comment("Marcel","cubeMaze","Testujem komentare1", date1));
        commentService.addComment(new Comment("Feri","cubeMaze","Testujem komentare2", date3));
        commentService.addComment(new Comment("Palo","tiles","Testujem komentare3", date4));
        commentService.addComment(new Comment("Kleopatra","cubeMaze","Testujem komentare4", date2));

        var comments = commentService.getComments("cubeMaze");

        assertEquals(3,comments.size());

        assertEquals("Marcel",comments.get(0).getPlayer());
        assertEquals("cubeMaze",comments.get(0).getGame());
        assertEquals("Testujem komentare1",comments.get(0).getComment());
        assertEquals(date1,comments.get(0).getCommentedAt());

        assertEquals("Kleopatra",comments.get(2).getPlayer());
        assertEquals("cubeMaze",comments.get(2).getGame());
        assertEquals("Testujem komentare4",comments.get(2).getComment());
        assertEquals(date2,comments.get(2).getCommentedAt());

        assertEquals("Feri",comments.get(1).getPlayer());
        assertEquals("cubeMaze",comments.get(1).getGame());
        assertEquals("Testujem komentare2",comments.get(1).getComment());
        assertEquals(date3,comments.get(1).getCommentedAt());

    }
}
