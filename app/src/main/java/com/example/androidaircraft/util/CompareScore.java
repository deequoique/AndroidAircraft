package com.example.androidaircraft.util;


import com.example.androidaircraft.player.Player;

import java.util.Comparator;

/**
 * @author 2001113
 * 按照分数排序
 */
public class CompareScore implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if(o1.score > o2.score){
            return -1;
        }
        else {
            return 1;
        }
    }
}
