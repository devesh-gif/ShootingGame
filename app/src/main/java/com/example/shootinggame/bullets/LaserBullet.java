package com.example.shootinggame.bullets;

import android.graphics.Bitmap;

public class LaserBullet extends Bullet {

    public LaserBullet(Bitmap bitmap) {

        super(5,30);

        bulletImage = bitmap;
    }

    public boolean canPierce() {

        return true;

    }
}