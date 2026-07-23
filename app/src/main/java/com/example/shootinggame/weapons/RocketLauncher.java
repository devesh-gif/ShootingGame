package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.RocketBullet;

import java.util.ArrayList;

public class RocketLauncher extends Weapon {

    public RocketLauncher() {
        super("Rocket Launcher", 80, 800, 12);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        Bullet rocket = new RocketBullet();

        if (facingRight) {
            rocket.shoot(x + 130, y + 90, true);
        } else {
            rocket.shoot(x, y + 90, false);
        }

        bullets.add(rocket);

        return bullets;
    }
}