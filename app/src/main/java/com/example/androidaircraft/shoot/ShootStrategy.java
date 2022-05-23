package com.example.androidaircraft.shoot;



import com.example.androidaircraft.aircraft.AbstractAircraft;
import com.example.androidaircraft.bullet.AbstractBullet;

import java.util.List;

public interface ShootStrategy {

    List<AbstractBullet> way(AbstractAircraft aircraft);
}
