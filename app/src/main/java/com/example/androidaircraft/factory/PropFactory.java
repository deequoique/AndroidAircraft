package com.example.androidaircraft.factory;

import android.util.Log;

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
        Log.i("propfactory",abstractProp.toString());
        return (abstractProp);
    }

    @Override
    public AbstractProp create() {

        double i = Math.random();
        PropFactory propFactory;
        AbstractProp abstractProp;
        double prop1 = 0.2;
        double prop2 = 0.4;
        double prop3 = 0.6;
        double prop4 = 0.7;
        //随机生成五种道具
        if (i<=prop1){
            propFactory = new HpFactory();
            abstractProp = propFactory.create();
        }
        else if (i<=prop2){
            propFactory = new BombFactory();
            abstractProp = propFactory.create();
        }
        else if(i<=prop3){
            propFactory = new FireFactory();
            abstractProp = propFactory.create();
        }
        else if(i<=prop4){
            propFactory = new GoldCoinFactory();
            abstractProp = propFactory.create();
        }
        else{
            Log.i("propfactory","create");

            propFactory = new SilverCoinFactory();
            abstractProp = propFactory.create();
        }
        return abstractProp;

    }
}
