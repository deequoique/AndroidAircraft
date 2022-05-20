package com.example.androidaircraft.shoot;



import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.bullet.AbstractBullet;
import com.example.androidaircraft.bullet.EnemyBullet;
import com.example.androidaircraft.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 200111013
 *
 * 普通射击策略：单线单点
 */
public class MobShoot implements ShootStrategy{

    @Override
    public List<AbstractBullet> way(AbstractAircraft aircraft) {
        List<AbstractBullet> res = new LinkedList<>();
        int x =aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int speedX = 0;
        int speedY;
        int shootNum = 1;
        AbstractBullet abstractBullet;
        if(aircraft instanceof HeroAircraft) {
            speedY = aircraft.getSpeedY() + aircraft.getDirection()*5;
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                abstractBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, aircraft.getPower());
                res.add(abstractBullet);
            }
        }
        else {
            for(int i=0; i<shootNum; i++){
                speedY = aircraft.getSpeedY() - aircraft.getDirection()*5;
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, aircraft.getPower());
                res.add(abstractBullet);
            }
        }
        return res;
    }
}
