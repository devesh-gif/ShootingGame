package com.example.shootinggame.weapons;

import android.graphics.Bitmap;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.LaserBullet;
import com.example.shootinggame.game.GameView;

import java.util.ArrayList;

public class Sniper extends Weapon {

    public Sniper() {
        super("Sniper", 9, 700, 40);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        Bullet bullet = new LaserBullet(
                GameView.sniperBullet
        );
        bullet.setRange(1000);

        if (facingRight) {
            bullet.shoot(x + 130, y + 90, true);
        } else {
            bullet.shoot(x, y + 90, false);
        }

        bullets.add(bullet);

        return bullets;
    }
}