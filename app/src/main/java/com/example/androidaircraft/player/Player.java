package com.example.androidaircraft.player;

import java.io.Serializable;

/**
 * @author 200111013
 */
public class Player implements Serializable {
    private int score;

    public int getGameMode() {
        return gameMode;
    }


    private final int gameMode;
    private String name;
    private final String time;
    public Player(int score, String time ,int gameMode){
        this.score = score;
        this.time = time;
        this.gameMode = gameMode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

}
