package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.NormalBullet;

import java.util.ArrayList;

public class Shotgun extends Weapon {

    public Shotgun() {
        super("Shotgun", 12, 500, 18);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        for (int i = -2; i <= 2; i++) {

            Bullet bullet = new NormalBullet(damage, bulletSpeed);

            if (facingRight) {
                bullet.shoot(x + 130, y + 90 + (i * 10), true);
            } else {
                bullet.shoot(x, y + 90 + (i * 10), false);
            }

            bullets.add(bullet);
        }

        return bullets;
    }
}