package com.example.androidaircraft.factory;


import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.BossEnemy;
import com.example.androidaircraft.application.ImageManager;

/**
 * @author 200111013
 */
public class BossFactory extends EnemyFactory{
    @Override
    public AbstractAircraft create() {
        return new BossEnemy(
                (int)(Math.random()*(MainActivity.screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * MainActivity.screenHeight * 0.2)*1,
                2, 0, 100);
    }
}
