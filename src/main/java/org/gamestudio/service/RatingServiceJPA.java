package org.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.gamestudio.entity.Rating;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@Transactional
public class RatingServiceJPA implements RatingService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) {

        entityManager.createQuery("DELETE FROM Rating r WHERE r.player =:playerName")
                .setParameter("playerName", rating.getPlayer()).executeUpdate();
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game){

        BigDecimal result = (BigDecimal) entityManager.createNativeQuery("select avg(rating) from rating where game = :game")
                .setParameter("game", game)
                .getSingleResult();
        if ( result == null) {
            return 0;
        }
        return result.intValue();
    }

    @Override
    public int getRating(String game, String player) {

        Rating rating = (Rating) entityManager.createQuery("select r from Rating r where r.game =:game and r.player =:player")
                .setParameter("game",game).setParameter("player",player)
                .getSingleResult();
        if ( rating == null ) return 0;
        else return rating.getRating();

    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM rating").executeUpdate();
    }
}
