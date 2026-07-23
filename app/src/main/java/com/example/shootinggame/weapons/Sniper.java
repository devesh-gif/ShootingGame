package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.LaserBullet;

import java.util.ArrayList;

public class Sniper extends Weapon {

    public Sniper() {
        super("Sniper", 60, 700, 40);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        Bullet bullet = new LaserBullet();

        if (facingRight) {
            bullet.shoot(x + 130, y + 90, true);
        } else {
            bullet.shoot(x, y + 90, false);
        }

        bullets.add(bullet);

        return bullets;
    }
}