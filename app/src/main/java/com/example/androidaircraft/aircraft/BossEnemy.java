package com.example.androidaircraft.aircraft;


import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.bullet.AbstractBullet;
import com.example.androidaircraft.shoot.SeperateShoot;
import com.example.androidaircraft.shoot.ShootContext;

import java.util.List;

/**
 * boss敌机，在关卡末尾出现
 * @author 200111013
 */

public class BossEnemy extends AbstractAircraft{


    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    private final int shootNum = 1;

    private final int power = 20;

    private final int direction = -1;


    @Override
    public List<AbstractBullet> shoot() {
        ShootContext context = new ShootContext(new SeperateShoot());
        return context.executrStrategy(this);
    }



    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.screenHeight ) {
            vanish();
        }
    }

}
