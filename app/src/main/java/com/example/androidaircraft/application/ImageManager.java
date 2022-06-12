package com.example.androidaircraft.application;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.androidaircraft.R;
import com.example.androidaircraft.aircraft.BossEnemy;
import com.example.androidaircraft.aircraft.EliteEnemy;
import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.aircraft.MobEnemy;
import com.example.androidaircraft.bullet.EnemyBullet;
import com.example.androidaircraft.bullet.HeroBullet;
import com.example.androidaircraft.props.BombSupply;
import com.example.androidaircraft.props.FireSupply;
import com.example.androidaircraft.props.GoldCoin;
import com.example.androidaircraft.props.HpSupply;
import com.example.androidaircraft.props.SilverCoin;

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
    public static Bitmap PROP_GOLD_IMAGE;
    public static Bitmap PROP_SILVER_IMAGE;
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
        ImageManager.PROP_GOLD_IMAGE = BitmapFactory.decodeResource(resources,R.drawable.coin_gold_dollar);
        ImageManager.PROP_SILVER_IMAGE = BitmapFactory.decodeResource(resources,R.drawable.coin_silver_dollar);
        CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
        CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
        CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
        CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);

        CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
        CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);

        CLASSNAME_IMAGE_MAP.put(HpSupply.class.getName(), PROP_BLOOD_IMAGE);
        CLASSNAME_IMAGE_MAP.put(FireSupply.class.getName(), PROP_BULLET_IMAGE);
        CLASSNAME_IMAGE_MAP.put(BombSupply.class.getName(), PROP_BOMB_IMAGE);
        CLASSNAME_IMAGE_MAP.put(GoldCoin.class.getName(),PROP_GOLD_IMAGE);
        CLASSNAME_IMAGE_MAP.put(SilverCoin.class.getName(),PROP_SILVER_IMAGE);
    }

    public static Bitmap get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }


    public static Bitmap get(Object obj){
        if (obj == null){
            return null;
        }
        else {
            return get(obj.getClass().getName());
        }
    }

}
