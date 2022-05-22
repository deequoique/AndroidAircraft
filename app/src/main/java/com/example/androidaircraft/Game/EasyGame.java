package com.example.androidaircraft.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.R;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.application.ImageManager;
import com.example.androidaircraft.basic.AbstractFlyingObject;
import com.example.androidaircraft.bullet.AbstractBullet;
import com.example.androidaircraft.factory.EnemyFactory;
import com.example.androidaircraft.player.Player;
import com.example.androidaircraft.props.AbstractProp;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class EasyGame extends AbstactGame implements Runnable{

    public EasyGame(MainActivity context) {
        super(context);
    }
    @Override
    protected void bossTime(){
    }
}