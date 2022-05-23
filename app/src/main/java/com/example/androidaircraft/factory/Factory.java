package com.example.androidaircraft.factory;

import com.example.androidaircraft.basic.AbstractFlyingObject;

/**
 * @author 200111013
 *
 */
public interface Factory {
    /**
     * 实现物品实例化
     * @return link abstract-object
     */
    AbstractFlyingObject create();
}
