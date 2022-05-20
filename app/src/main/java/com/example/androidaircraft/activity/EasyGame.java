package com.example.androidaircraft.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import com.example.androidaircraft.MainActivity;
import com.example.androidaircraft.R;
import com.example.androidaircraft.application.ImageManager;
import com.example.androidaircraft.basic.AbstractFlyingObject;

import java.util.List;

public class EasyGame extends AppCompatActivity {
    private Canvas canvas = new Canvas();
    private int backGroundTop = 0;
    public void draw(Canvas g){
        Paint mPaint = new Paint();
        g.drawBitmap(ImageManager.BACKGROUND_IMAGE,0, (float) (this.backGroundTop - MainActivity.screenHeight),mPaint);
        g.drawBitmap(ImageManager.BACKGROUND_IMAGE,0,this.backGroundTop,mPaint);
        backGroundTop += 1;
        if(backGroundTop == MainActivity.screenHeight){
            this.backGroundTop = 0;
        }
//        // 先绘制道具，再子弹，后绘制飞机
//        // 这样子弹显示在飞机的下层
//
//        paintImageWithPositionRevised(g,abstractProp);
//
//        paintImageWithPositionRevised(g, enemyBullets);
//        paintImageWithPositionRevised(g, heroBullets);
//
//        paintImageWithPositionRevised(g, enemyAircrafts);
//
//        g.drawBitmap(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
//                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);
//
//        //绘制得分和生命值
//        paintScoreAndLife(g);
    }
    private void paintImageWithPositionRevised(Canvas g, List<? extends AbstractFlyingObject> objects) {

        if (objects.size() == 0) {
            return;
        }
        Paint paint = new Paint();

        for (AbstractFlyingObject object : objects) {
            Bitmap image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawBitmap(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, paint);
        }
    }


    public void loadingImg(){
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        ImageManager.HERO_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        ImageManager.BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.boss);
        ImageManager.ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.elite);
        ImageManager.ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_enemy);
        ImageManager.HERO_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_hero);
        ImageManager.MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.mob);
        ImageManager.PROP_BLOOD_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_blood);
        ImageManager.PROP_BOMB_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_bomb);
        ImageManager.PROP_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_bullet);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);
        draw(canvas);
    }
}