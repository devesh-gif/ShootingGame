package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.GrenadeBullet;

import java.util.ArrayList;

public class GrenadeLauncher extends Weapon {

    public GrenadeLauncher() {
        super("Grenade Launcher", 60, 1000, 10);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        Bullet grenade = new GrenadeBullet();

        if (facingRight) {
            grenade.shoot(x + 130, y + 90, true);
        } else {
            grenade.shoot(x, y + 90, false);
        }

        bullets.add(grenade);

        return bullets;
    }
}