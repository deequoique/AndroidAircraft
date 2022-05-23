package com.example.androidaircraft.factory;


import com.example.androidaircraft.props.AbstractProp;
import com.example.androidaircraft.props.FireSupply;

/**
 * @author 200111013
 */
public class FireFactory extends PropFactory{
    @Override
    public AbstractProp create() {
        return new FireSupply();
    }
}
