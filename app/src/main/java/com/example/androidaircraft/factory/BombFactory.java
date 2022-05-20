package com.example.androidaircraft.factory;


import com.example.androidaircraft.props.AbstractProp;
import com.example.androidaircraft.props.BombSupply;

/**
 * @author 200111013
 */
public class BombFactory extends PropFactory{
    @Override
    public AbstractProp create() {
        return new BombSupply();
    }
}
