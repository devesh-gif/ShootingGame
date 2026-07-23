package com.example.shootinggame.bullets;

public class RocketBullet extends Bullet {

    private final int explosionRadius = 120;
    private boolean exploded = false;

    public RocketBullet() {
        super(80, 12);
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }

    public boolean hasExploded() {
        return exploded;
    }

    public void explode() {

        exploded = true;

        deactivate();
    }
}