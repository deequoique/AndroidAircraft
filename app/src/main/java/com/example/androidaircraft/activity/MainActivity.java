package com.example.androidaircraft.activity;

import android.os.Build;
import android.os.Bundle;

import com.example.androidaircraft.Game.AbstactGame;
import com.example.androidaircraft.Game.EasyGame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;


import com.example.androidaircraft.R;
import com.example.androidaircraft.application.ImageManager;
import com.example.androidaircraft.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static  String TAG  = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
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
    }

    public void Starteasy(View view) {
        EasyGame game = new EasyGame(this);
       setContentView(game);
       new Thread(game).start();
    }
}