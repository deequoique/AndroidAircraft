package com.example.androidaircraft.aircraft;


import com.example.androidaircraft.MainActivity;
import com.example.androidaircraft.application.ImageManager;
import com.example.androidaircraft.bullet.AbstractBullet;
import com.example.androidaircraft.shoot.MobShoot;
import com.example.androidaircraft.shoot.ShootContext;
import com.example.androidaircraft.shoot.ShootStrategy;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    private static final HeroAircraft instance = new HeroAircraft(
            MainActivity.screenWidth / 2,
            MainActivity.screenHeight - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0, 100000);

//    public static HeroAircraft getInstance(){
//        return instance;
//    }

    /**攻击方式 */


    /**
     * 子弹伤害
     */
    private final int power = 30;

    /**
     * 子弹射击方向 (向下发射：1，向上发射：-1)
     */
    private final int direction = -1;

    public ShootStrategy getStrategy() {
        return strategy;
    }

    /**
     * 子弹攻击方式
     */
    private ShootStrategy strategy = new MobShoot();

    public void setStrategy(ShootStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<AbstractBullet> shoot() {
        ShootContext context = new ShootContext(strategy);
        return context.executrStrategy(this);
    }


}
