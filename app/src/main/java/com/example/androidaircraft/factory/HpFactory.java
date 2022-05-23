package com.example.androidaircraft.factory;


import com.example.androidaircraft.props.AbstractProp;
import com.example.androidaircraft.props.HpSupply;

/**
 * @author 200111013
 */
public class HpFactory extends PropFactory{
    @Override
    public AbstractProp create() {
        return new HpSupply();
    }
}
