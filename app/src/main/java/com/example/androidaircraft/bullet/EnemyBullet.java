package com.example.androidaircraft.bullet;

/**
 * @Author hitsz
 */
public class EnemyBullet extends AbstractBullet {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public int bomb() {
        vanish();
        return 0;
    }
}
