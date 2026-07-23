package com.example.shootinggame.bullets;

public class GrenadeBullet extends Bullet {

    private int timer = 120;
    private final int explosionRadius = 100;

    public GrenadeBullet() {
        super(60, 10);
    }

    @Override
    public void move() {

        if (!active)
            return;

        x += speed * direction;

        y += 2;

        timer--;

        if (timer <= 0)
            deactivate();
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }

    public boolean shouldExplode() {
        return timer <= 0;
    }
}