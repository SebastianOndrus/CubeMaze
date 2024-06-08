package org.gamestudio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Score {

    @Id
    @GeneratedValue
    private int ident;

    private String player;
    private String game;

    private int level;
    private int points;
    private Date playedAt;

    public Score(){

    }

    public Score(String player, String game, int level, int points, Date playedAt) {
        this.player = player;
        this.game = game;
        this.level = level;
        this.points = points;
        this.playedAt = playedAt;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        return "Score{" +
                "ident=" + ident +
                ", player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", level=" + level +
                ", points=" + points +
                ", playedAt=" + playedAt +
                '}';
    }
}
