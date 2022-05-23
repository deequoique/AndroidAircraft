package com.example.androidaircraft.shoot;


import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.bullet.AbstractBullet;

import java.util.List;

/**
 * @author 200111013
 * 射击策略context
 */
public class ShootContext {

    private ShootStrategy strategy;

    public ShootContext(ShootStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(ShootStrategy strategy){
        this.strategy = strategy;
    }

    public List<AbstractBullet> executrStrategy(AbstractAircraft aircraft){
        return strategy.way(aircraft);
    }

}
