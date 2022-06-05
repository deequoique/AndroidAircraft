package com.example.androidaircraft.game;

import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.BossEnemy;
import com.example.androidaircraft.bullet.AbstractBullet;

public class NormalGame extends AbstactGame{
    public NormalGame(MainActivity context) {
        super(context);
    }

    private int i = 0;
    private int growNumber = 0;
    @Override
    protected void grow(){

        i++;
        if (i % 100 == 0){
            growNumber++;
            enemyMaxNumber += 1;
            for (AbstractBullet enemyBullet:enemyBullets){
                enemyBullet.setPower((enemyBullet.getPower() + 1));
            }
            for (AbstractAircraft enemy: enemyAircrafts){
                if(!(enemy instanceof BossEnemy)){
                    enemy.setHp(enemy.getHp() + 1);
                }
            }
            enemyFactory.prob -= 0.01;
            bossScoreThreshold -= 1;
            System.out.println("难度增加，敌机数量最大值为"+enemyMaxNumber+"，精英敌机产生概率为"+(1-enemyFactory.prob)+"，敌机血量增加了"+growNumber+"，敌机子弹伤害增加了"+growNumber+"，boss出现阙值为"+bossScoreThreshold);
        }

    }
}
