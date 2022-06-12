package com.example.androidaircraft.game;

import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.BossEnemy;
import com.example.androidaircraft.bullet.AbstractBullet;

public class EasyGame extends AbstactGame implements Runnable{

    public EasyGame(MainActivity context) {
        super(context);
    }
    @Override
    protected void grow(){


    }
    @Override
    protected void bossTime(){

        bossExist = false;

    }
}