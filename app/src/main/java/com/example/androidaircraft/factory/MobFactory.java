package com.example.androidaircraft.factory;


import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.MobEnemy;
import com.example.androidaircraft.application.ImageManager;

/**
 * @author 200111013
 */
public class MobFactory extends EnemyFactory{
    @Override
    public AbstractAircraft create() {
        return new MobEnemy(
                (int) ( Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * MainActivity.screenWidth * 0.2)*1,
                0,
                30,
                30);
    }
}
