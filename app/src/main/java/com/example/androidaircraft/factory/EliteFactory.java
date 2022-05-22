package com.example.androidaircraft.factory;


import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.EliteEnemy;
import com.example.androidaircraft.application.ImageManager;

import java.util.Random;

/**
 * @author 200111013
 */
public class EliteFactory extends EnemyFactory{
    @Override
    public AbstractAircraft create() {
        Random ran = new Random();
        return new EliteEnemy(
                (int)(Math.random()*(MainActivity.screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * MainActivity.screenHeight * 0.2)*1,
                ran.nextInt(10),
                8,
                50);
    }
}
