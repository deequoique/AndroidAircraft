package com.example.androidaircraft.factory;

import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.props.AbstractProp;

/**
 * @author 200111013
 */


public class PropFactory implements Factory{

    public static AbstractProp prop(AbstractAircraft enemyAircraft){
        PropFactory factory = new PropFactory();
        AbstractProp abstractProp = factory.create();
        abstractProp.setLocation(enemyAircraft.getLocationX(),enemyAircraft.getLocationY());
        return (abstractProp);
    }

    @Override
    public AbstractProp create() {

        double i = Math.random();
        PropFactory propFactory;
        AbstractProp abstractProp;
        double prop1 = 0.1;
        double prop2 = 0.2;
        //随机生成三种道具
        if (i<=prop1){
            propFactory = new HpFactory();
            abstractProp = propFactory.create();
        }
        else if (i<=prop2){
            propFactory = new BombFactory();
            abstractProp = propFactory.create();
        }
        else{
            propFactory = new FireFactory();
            abstractProp = propFactory.create();
        }
        return abstractProp;

    }
}
