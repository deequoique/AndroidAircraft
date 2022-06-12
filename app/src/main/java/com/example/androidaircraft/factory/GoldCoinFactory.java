package com.example.androidaircraft.factory;

import com.example.androidaircraft.props.AbstractProp;
import com.example.androidaircraft.props.GoldCoin;

public class GoldCoinFactory extends PropFactory{

    @Override
    public AbstractProp create(){return new GoldCoin();
    }
}
