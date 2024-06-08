package org.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.gamestudio.entity.Comment;

import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService{


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Comment comment)  {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game)  {
        return entityManager.createQuery("select c from Comment c where c.game = :game order by c.commentedAt desc")
                .setParameter("game", game)
                .getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM comment").executeUpdate();
    }
}
