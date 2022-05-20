package com.example.androidaircraft;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.androidaircraft.activity.AbstactGameActivity;
import com.example.androidaircraft.activity.EasyGame;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;


import com.example.androidaircraft.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

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
        setContentView(R.layout.activity_main);
    }

    public void Starteasy(View view) {
        startActivity(new Intent(this, EasyGame.class));
    }
}