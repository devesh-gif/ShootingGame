package com.example.shootinggame.bullets;

import android.graphics.Bitmap;

public class NormalBullet extends Bullet {

    public NormalBullet(int damage,
                        int speed,
                        Bitmap bitmap) {

        super(damage, speed);

        bulletImage = bitmap;
    }

}