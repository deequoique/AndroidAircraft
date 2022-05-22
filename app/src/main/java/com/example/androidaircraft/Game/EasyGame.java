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

    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private final Paint paint = new Paint();
    public int gameMode;


    private int backGroundTop = 0;


    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;

    private  HeroAircraft heroAircraft;

    protected final List<AbstractAircraft> enemyAircrafts;
    private final List<AbstractBullet> heroBullets;
    protected final List<AbstractBullet> enemyBullets;
    private final List<AbstractProp> abstractProp;
    public int bossScoreThreshold = 300;

    public Player getPlayer() {
        return player;
    }

    private Player player;

    protected int enemyMaxNumber = 5;

    protected boolean bossExist = false;


    private int score = 0;
    private int time = 0;
    public int scorer = 0;
    protected EnemyFactory enemyFactory = new EnemyFactory();
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private final int cycleDuration = 600;
    private int cycleTime = 0;



    public EasyGame(MainActivity context) {

        super(context);

        heroAircraft = HeroAircraft.getInstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        abstractProp = new LinkedList<>();


        /*
          Scheduled 线程池，用于定时任务调度
          关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
          apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        ThreadFactory gameThread = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread t = new Thread(runnable);
                t.setName("game thread");
                return t;
            }
        };
        this.executorService = new ScheduledThreadPoolExecutor(1,gameThread);
//        //启动触摸监听
////TODO
//
//        surfaceHolder = this.getHolder();
//        surfaceHolder.addCallback((SurfaceHolder.Callback) this);
//        this.setFocusable(true);
    }


//    public void draw() {
//        surfaceHolder = this.getHolder();
//        canvas = surfaceHolder.lockCanvas();
//        super.draw(canvas);
//        if (canvas==null) return;
//
//        // 循环绘制背景图片
//        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE,0,
//                backGroundTop-ImageManager.BACKGROUND_IMAGE.getHeight(),paint);
//        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE,0,backGroundTop,paint);
//        backGroundTop+=1;
//        if (backGroundTop==ImageManager.BACKGROUND_IMAGE.getHeight()) backGroundTop=0;
//        backGroundTop += 1;
//        if (backGroundTop == MainActivity.screenHeight) {
//            this.backGroundTop = 0;
//        }
//        surfaceHolder.unlockCanvasAndPost(canvas);
//        // 先绘制道具，再子弹，后绘制飞机
//        // 这样子弹显示在飞机的下层
//
//        paintImageWithPositionRevised(canvas,abstractProp);
//
//        paintImageWithPositionRevised(canvas, enemyBullets);
//        paintImageWithPositionRevised(canvas, heroBullets);
//
//        paintImageWithPositionRevised(canvas, enemyAircrafts);
//
//        canvas.drawBitmap(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
//                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);
//
//        //绘制得分和生命值
//        paintScoreAndLife(canvas);
//    }
//    private void paintImageWithPositionRevised(Canvas g, List<? extends AbstractFlyingObject> objects) {
//
//        if (objects.size() == 0) {
//            return;
//        }
//        Paint paint = new Paint();
//
//        for (AbstractFlyingObject object : objects) {
//            Bitmap image = object.getImage();
//            assert image != null : objects.getClass().getName() + " has no image! ";
//            g.drawBitmap(image, object.getLocationX() - image.getWidth() / 2,
//                    object.getLocationY() - image.getHeight() / 2, paint);
//        }
//    }




//    @Override
//    public void run() {
//        loadingImg();
//        draw();
//
//    }
}