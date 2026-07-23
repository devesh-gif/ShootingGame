package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.NormalBullet;

import java.util.ArrayList;

public class Pistol extends Weapon {

    public Pistol() {
        super("Pistol", 20, 150, 20);
    }


    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        Bullet bullet = new NormalBullet(damage, bulletSpeed);

        if (facingRight) {
            bullet.shoot(x + 130, y + 90, true);
        } else {
            bullet.shoot(x, y + 90, false);
        }

        bullets.add(bullet);

        return bullets;
    }
}