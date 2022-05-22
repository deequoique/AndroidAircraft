package com.example.androidaircraft.application;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.androidaircraft.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, Bitmap> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static Bitmap BACKGROUND_IMAGE;
    public static Bitmap HERO_IMAGE;
    public static Bitmap HERO_BULLET_IMAGE;
    public static Bitmap ENEMY_BULLET_IMAGE;
    public static Bitmap MOB_ENEMY_IMAGE;
    public static Bitmap ELITE_ENEMY_IMAGE;
    public static Bitmap BOSS_ENEMY_IMAGE;

    public static Bitmap PROP_BLOOD_IMAGE;
    public static Bitmap PROP_BOMB_IMAGE;
    public static Bitmap PROP_BULLET_IMAGE;
    public static void loadingImg(Resources resources){
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.bg);
        ImageManager.HERO_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.hero);
        ImageManager.BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.boss);
        ImageManager.ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.elite);
        ImageManager.ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.bullet_enemy);
        ImageManager.HERO_BULLET_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.bullet_hero);
        ImageManager.MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.mob);
        ImageManager.PROP_BLOOD_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.prop_blood);
        ImageManager.PROP_BOMB_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.prop_bomb);
        ImageManager.PROP_BULLET_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.prop_bullet);
    }

    public static Bitmap get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }


    public static Bitmap get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
