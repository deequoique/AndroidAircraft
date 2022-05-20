package com.example.androidaircraft.activity;

import static com.example.androidaircraft.factory.PropFactory.prop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidaircraft.MainActivity;
import com.example.androidaircraft.R;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.BossEnemy;
import com.example.androidaircraft.aircraft.EliteEnemy;
import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.application.ImageManager;
import com.example.androidaircraft.basic.AbstractFlyingObject;
import com.example.androidaircraft.bullet.AbstractBullet;
import com.example.androidaircraft.factory.EnemyFactory;
import com.example.androidaircraft.player.Player;
import com.example.androidaircraft.props.AbstractProp;
import com.example.androidaircraft.props.BombSupply;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AbstactGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_game);
    }
    public static AbstactGameActivity game = new AbstactGameActivity();
    public int gameMode;

    private int backGroundTop = 0;

    private Canvas canvas;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;


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

    public AbstactGameActivity() {
        heroAircraft = new HeroAircraft(
                MainActivity.screenWidth / 2,
                MainActivity.screenHeight - ImageManager.HERO_IMAGE.getHeight() ,
                0, 0, 100000);
//        heroAircraft = HeroAircraft.getInstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        abstractProp = new LinkedList<>();



        /*
          Scheduled 线程池，用于定时任务调度
          关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
          apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1);
        //启动英雄机鼠标监听
//TODO
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {


        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {

                System.out.println(time);
                //Boss检测
                bossTime();


                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    enemyAircrafts.add(enemyFactory.create());
                }
                // 飞机射出子弹
                shootAction();
            }

            //难度增加
            grow();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            draw(canvas);

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();

                //创建player
                createPlayer();

            }
        };

        /*
          以固定延迟时间进行执行
          本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
    }

    //***********************
    //      Action 各部分
    //***********************




    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        //  敌机射击
        for(AbstractAircraft e:enemyAircrafts){
            enemyBullets.addAll(e.shoot());
        }


        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (AbstractBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (AbstractBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propMoveAction(){
        for (AbstractProp prop : abstractProp) {
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        //  敌机子弹攻击英雄
        for (AbstractBullet enemybullet : enemyBullets) {
            if (heroAircraft.crash(enemybullet)) {
                heroAircraft.decreaseHp(enemybullet.getPower());
                enemybullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (AbstractBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        //  获得分数，产生道具补给
                        score += 10;
                        scorer += 10;
                        if (enemyAircraft instanceof EliteEnemy) {
                            score += 10;
                            scorer += 10;
                            // 可能不会生成道具
                            if (Math.random() >= 0.3) {
                                abstractProp.add(prop(enemyAircraft));
                            }
                        }
                        if (enemyAircraft instanceof BossEnemy) {
                            score += 40;
                            scorer += 40;
                            abstractProp.add(prop(enemyAircraft));
                            abstractProp.add(prop(enemyAircraft));
                            abstractProp.add(prop(enemyAircraft));
                            abstractProp.add(prop(enemyAircraft));
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }


        // 我方获得道具，道具生效

        for (AbstractProp p : abstractProp) {
            if (heroAircraft.crash(p)) {
                p.vanish();
                p.use(heroAircraft);
                if (p instanceof BombSupply) {
                    score += ((BombSupply) p).score;
                    scorer += ((BombSupply) p).score;
                }
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        abstractProp.removeIf(AbstractProp::notValid);

    }


    /**
     * 发送boss产生信号给敌机工厂
     */
    protected void bossTime(){
        bossExist = false;

        boolean flag = false;
        if(this.scorer >= bossScoreThreshold){
            flag = true;
            this.scorer = this.scorer-bossScoreThreshold;
        }

        for (AbstractAircraft enemy:enemyAircrafts){
            if (enemy instanceof BossEnemy) {
                bossExist = true;
                break;
            }
        }
        if ( !bossExist && flag){

            enemyFactory.boss = true;
            enemyAircrafts.add(enemyFactory.create());
            bossExist = true;
        }
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     */




    public void draw(Canvas g){
        Paint mPaint = new Paint();
        g.drawBitmap(ImageManager.BACKGROUND_IMAGE,0, (float) (this.backGroundTop - MainActivity.screenHeight),mPaint);
        g.drawBitmap(ImageManager.BACKGROUND_IMAGE,0,this.backGroundTop,mPaint);
        backGroundTop += 1;
        if(backGroundTop == MainActivity.screenHeight){
            this.backGroundTop = 0;
        }
        // 先绘制道具，再子弹，后绘制飞机
        // 这样子弹显示在飞机的下层

        paintImageWithPositionRevised(g,abstractProp);

        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawBitmap(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);
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

    private void paintScoreAndLife(Canvas g) {
        Paint paint = new Paint();
        int x = 10;
        int y = 25;
        g.drawColor(0x00CBEF);
        g.drawText("SCORE:" + this.score, x, y,paint);
        y = y + 20;
        g.drawText("LIFE:" + this.heroAircraft.getHp(), x, y,paint);
    }

    /**
     * 创建player
     */
    private void createPlayer(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        player = new Player(this.score,dateFormat.format(date),gameMode);
    }

    public List<AbstractAircraft> getEnemyAircrafts() {
        return enemyAircrafts;
    }

    public List<AbstractBullet> getEnemyBullets() {
        return enemyBullets;
    }

    protected void grow(){

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

}
