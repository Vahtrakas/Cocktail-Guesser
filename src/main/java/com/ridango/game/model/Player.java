package com.ridango.game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    
    private Long playerScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(Long playerScore) {
        this.playerScore = playerScore;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", playerName=" + playerName + "]";
    }

    
}
