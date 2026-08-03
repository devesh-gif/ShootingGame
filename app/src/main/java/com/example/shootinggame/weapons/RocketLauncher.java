package com.example.shootinggame.weapons;

import android.graphics.Bitmap;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.bullets.RocketBullet;
import com.example.shootinggame.game.GameView;

import java.util.ArrayList;

public class RocketLauncher extends Weapon {

    public RocketLauncher() {
        super("Rocket Launcher", 19, 800, 12);
    }

    @Override
    public ArrayList<Bullet> fire(int x, int y, boolean facingRight) {

        ArrayList<Bullet> bullets = new ArrayList<>();

        Bullet rocket = new RocketBullet(
                GameView.rocketBullet
        );
        rocket.setRange(800);


        if (facingRight) {
            rocket.shoot(x + 130, y + 90, true);
        } else {
            rocket.shoot(x, y + 90, false);
        }

        bullets.add(rocket);

        return bullets;
    }
}