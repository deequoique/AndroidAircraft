package com.example.androidaircraft.props;


import com.example.androidaircraft.Game.AbstactGame;
import com.example.androidaircraft.activity.MainActivity;
import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.aircraft.HeroAircraft;
import com.example.androidaircraft.basic.AbstractFlyingObject;
import com.example.androidaircraft.bullet.AbstractBullet;
import com.example.androidaircraft.publisher.BombPublish;

import java.util.List;

/**
 * @author 200111013
 */
public class BombSupply extends AbstractProp{
    public int score = 0;


    public BombSupply() {
    }

    @Override
    public void use(HeroAircraft heroAircraft) {
//        BombPublish bombPublish = new BombPublish();
//        List<AbstractAircraft> enemyAircrafts=  MainActivity.game.getEnemyAircrafts();
//        List<AbstractBullet> enemyBullet = AbstactGame.game.getEnemyBullets();
//        for (AbstractFlyingObject fobj : enemyAircrafts){
//            bombPublish.addList(fobj);
//        }
//        for (AbstractFlyingObject fobj : enemyBullet){
//            bombPublish.addList(fobj);
//        }
//        bombPublish.notifySubscribers();
//        score = bombPublish.score;
    }
}
