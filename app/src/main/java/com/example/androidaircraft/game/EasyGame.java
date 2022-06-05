package com.example.androidaircraft.game;

import com.example.androidaircraft.activity.MainActivity;

public class EasyGame extends AbstactGame implements Runnable{

    public EasyGame(MainActivity context) {
        super(context);
    }
    @Override
    protected void bossTime(){
    }
}