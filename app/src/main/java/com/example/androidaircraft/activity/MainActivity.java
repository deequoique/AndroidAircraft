package com.example.androidaircraft.activity;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.example.androidaircraft.game.AbstactGame;
import com.example.androidaircraft.game.EasyGame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;


import com.example.androidaircraft.game.HardGame;
import com.example.androidaircraft.game.NormalGame;
import com.example.androidaircraft.R;
import com.example.androidaircraft.application.ImageManager;

public class MainActivity extends AppCompatActivity {
    private static  String TAG  = MainActivity.class.getSimpleName();
    public static AbstactGame game;

    private boolean needMusic;
    private Switch bgm ;
    public static  int screenWidth ;
    public static  int screenHeight ;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void getScreenHW(){


        screenWidth = getWindowManager().getCurrentWindowMetrics().getBounds().width();
        Log.i(TAG,"screenWidth:"+screenWidth);

        screenHeight = getWindowManager().getCurrentWindowMetrics().getBounds().height();
        Log.i(TAG,"screenWidth:"+screenHeight);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getScreenHW();
        super.onCreate(savedInstanceState);
        ImageManager.loadingImg(getResources());
        setContentView(R.layout.activity_main);
        bgm = (Switch) findViewById(R.id.bgm);

        bgm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                needMusic = b;
            }
        });
    }

    public void Starteasy(View view) {
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);
        game = new EasyGame(this);
       new Thread(game).start();
       setContentView(game);
       if(needMusic){
           game.setNeedMusic(true);
       }
    }

    public void Startnormal(View view) {
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(),R.drawable.bg3);
        game = new NormalGame(this);
        setContentView(game);
        new Thread(game).start();
        if(needMusic){
            game.setNeedMusic(true);
        }
    }

    public void Starthard(View view) {
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(),R.drawable.bg4);
        game = new HardGame(this);
        setContentView(game);
        new Thread(game).start();
        if(needMusic){
            game.setNeedMusic(true);
        }
    }

}
