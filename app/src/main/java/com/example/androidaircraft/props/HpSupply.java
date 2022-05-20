package com.example.androidaircraft.props;


import com.example.androidaircraft.aircraft.HeroAircraft;

/**
 * @author 200111013
 */
public class HpSupply extends AbstractProp{

    public HpSupply(){
    }

    @Override
    public void use(HeroAircraft heroAircraft) {
        heroAircraft.raiseHp(20);
    }

}
