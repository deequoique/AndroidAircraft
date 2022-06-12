package com.example.androidaircraft.player;

import java.io.Serializable;

/**
 * @author 200111013
 */
public class Player implements Serializable {
    private static final Player instance = new Player();

    public int score;
    public int money;
    public int power;
    public int hp;
    public int getGameMode() {
        return gameMode;
    }

    public int gameMode = 0;
    public String name;


    public String passWord;
    public String time = null;

    private Player(){
    }
    public void increaseCoin(int increase){
        money +=increase;
    }
    public static Player getInstance(){
        return instance;
    }
}
