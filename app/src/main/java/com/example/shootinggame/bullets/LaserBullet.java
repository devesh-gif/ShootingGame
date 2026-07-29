package com.example.shootinggame.bullets;

public class LaserBullet extends Bullet {

    public LaserBullet(int damage, int speed) {
        super(damage, speed);
    }

    public boolean canPierce() {
        return true;
    }
}