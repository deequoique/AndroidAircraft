package com.example.androidaircraft.basic;

import android.view.MotionEvent;
import android.view.View;

import com.example.androidaircraft.Game.AbstactGame;
import com.example.androidaircraft.aircraft.HeroAircraft;

public class HeroController implements View.OnTouchListener {
    private AbstactGame game;
    private HeroAircraft heroAircraft;

    public HeroController(AbstactGame game, HeroAircraft heroAircraft){
        this.game = game;
        this.heroAircraft = heroAircraft;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            heroAircraft.setLocation(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }
}
