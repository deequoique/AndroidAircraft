package com.example.androidaircraft.factory;

import android.nfc.Tag;
import android.util.Log;

import com.example.androidaircraft.props.AbstractProp;
import com.example.androidaircraft.props.SilverCoin;

public class SilverCoinFactory extends PropFactory{

    @Override
    public AbstractProp create(){

        SilverCoin silverCoin = new SilverCoin();
        Log.i("silver","create");
        return silverCoin;
    }
}
