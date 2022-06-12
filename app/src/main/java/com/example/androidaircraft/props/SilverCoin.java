package com.example.androidaircraft.props;

import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.player.Player;

public class SilverCoin extends AbstractProp{
    public SilverCoin(){}

    @Override
    public void use(HeroAircraft heroAircraft) {
        Player.getInstance().increaseCoin(5);
    }
}
