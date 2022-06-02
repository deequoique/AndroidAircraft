package com.example.androidaircraft.application;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.androidaircraft.R;

import java.lang.reflect.Array;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MusicService extends Service {
    private ArrayList<MediaPlayer> players = new ArrayList<>();
    private MediaPlayer bgmPlayer;
    private MediaPlayer bossPlayer;
    private MediaPlayer bombPlayer;
    private MediaPlayer bulletHitPlayer;
    private MediaPlayer gameOverPlayer;
    private MediaPlayer getSupplyPlayer;

    public int onStartCommand(Intent intent , int flags, int startId){
        String action = intent.getStringExtra("action");
        if("bgm".equals(action)){
            playBgm();
        }else if ("boss".equals(action)){
            playBossBgm();
        }else if("bomb".equals(action)){
            playBomb();
        }else if("bullet".equals(action)){
            playBullet();
        }else if("over".equals(action)){
            playGameOver();
        }else if("supply".equals(action)){
            playSupply();
        }else if("stop".equals(action)){
            stopMusic();
        }else if("stop_boss".equals(action)){
            stopMusic(bossPlayer);
        }else if("stop_bgm".equals(action)){
            stopMusic(bgmPlayer);
        }
        return super.onStartCommand(intent,flags,startId);
    }

    public void playBgm(){
        if(bgmPlayer == null){
            bgmPlayer = MediaPlayer.create(this, R.raw.bgm);
            players.add(bgmPlayer);
        }

        bgmPlayer.start();
    }

    public void playBossBgm(){
        if(bossPlayer == null){
            bossPlayer = MediaPlayer.create(this,R.raw.bgm_boss);
            players.add(bossPlayer);
        }

        bossPlayer.start();
    }

    public void playBomb(){
        if(bombPlayer == null){
            bombPlayer = MediaPlayer.create(this, R.raw.bomb_explosion);
            players.add(bombPlayer);
        }

        bombPlayer.start();
    }

    public void playBullet(){
        if(bulletHitPlayer == null){
            bulletHitPlayer = MediaPlayer.create(this, R.raw.bullet_hit);
            players.add(bulletHitPlayer);
        }

        bulletHitPlayer.start();
    }

    public void playGameOver(){
        if(gameOverPlayer == null){
            gameOverPlayer = MediaPlayer.create(this, R.raw.game_over);
            players.add(gameOverPlayer);
        }

        gameOverPlayer.start();
    }

    public void playSupply(){
        if(getSupplyPlayer == null){
            getSupplyPlayer = MediaPlayer.create(this, R.raw.get_supply);
            players.add(getSupplyPlayer);
        }

        getSupplyPlayer.start();
    }

    public void stopMusic(MediaPlayer player){
        if (player != null) {
            player.stop();
            player.reset();//重置
            player.release();//释放
            player = null;
        }
    }

    public void stopMusic(){
        for (MediaPlayer player : players){
            if (player != null) {
                player.stop();
                player.reset();//重置
                player.release();//释放
                player = null;
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
