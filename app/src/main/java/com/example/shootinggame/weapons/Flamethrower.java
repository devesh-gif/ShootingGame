package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.FlameBullet;

import java.util.ArrayList;

public class Flamethrower extends Weapon {

    public Flamethrower() {
        super("Flamethrower", 10, 40, 8);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        for (int i = -2; i <= 2; i++) {

            Bullet flame = new FlameBullet();

            if (facingRight) {
                flame.shoot(x + 130, y + 90 + (i * 8), true);
            } else {
                flame.shoot(x, y + 90 + (i * 8), false);
            }

            bullets.add(flame);
        }

        return bullets;
    }
}