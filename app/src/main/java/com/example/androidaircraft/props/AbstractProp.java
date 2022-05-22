package com.example.androidaircraft.props;


import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.basic.AbstractFlyingObject;

/**
 * @author 200111013
 */
public abstract class AbstractProp extends AbstractFlyingObject {




    public AbstractProp(){this.speedY = 2;}

    /**
     * 实现用途
     * @param heroAircraft 英雄机实例
     */
    abstract public void use(HeroAircraft heroAircraft);
    @Override
    public void forward() {
        super.forward();
        if (locationY >= MainActivity.screenHeight ) {
            vanish();
        }
    }
}
