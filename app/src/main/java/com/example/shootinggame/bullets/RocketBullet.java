package com.example.shootinggame.bullets;

import android.graphics.Bitmap;

public class RocketBullet extends Bullet {

    public RocketBullet(Bitmap bitmap) {

        super(8,12);

        bulletImage = bitmap;

    }
    private int explosionRadius = 120;

    public int getExplosionRadius() {
        return explosionRadius;
    }
}