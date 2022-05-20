package com.example.androidaircraft.aircraft;



import com.example.androidaircraft.MainActivity;
import com.example.androidaircraft.bullet.AbstractBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.screenHeight ) {
            vanish();
        }
    }

    @Override
    public int bomb() {
        vanish();
        return 10;
    }

    @Override
    public List<AbstractBullet> shoot() {
        return new LinkedList<>();
    }

}
