package com.example.shootinggame.bullets;

public class LaserBullet extends Bullet {

    public LaserBullet() {
        super(60, 30);
    }

    public boolean canPierce() {
        return true;
    }
}