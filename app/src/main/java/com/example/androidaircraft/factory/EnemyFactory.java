package com.example.androidaircraft.factory;

import com.example.androidaircraft.aircraft.AbstractAircraft;

/**
 * @author 200111013
 */
public class EnemyFactory implements Factory{
    public boolean boss = false;
    public double prob = 0.7;
    @Override
    public AbstractAircraft create() {
        EnemyFactory enemyFactory;
        AbstractAircraft abstractAircraft;


        if(boss){
            enemyFactory = new BossFactory();
            boss = false;
        }
        else if(Math.random()<prob){
            enemyFactory = new MobFactory();
        }
        else {
            enemyFactory = new EliteFactory();
        }
        abstractAircraft = enemyFactory.create();
        return abstractAircraft;
    }
}
