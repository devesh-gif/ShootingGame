package com.example.shootinggame.bullets;

public class FlameBullet extends Bullet {

    private int life = 35;

    public FlameBullet() {
        super(10, 8);
    }

    @Override
    public void move() {

        if (!active)
            return;

        x += speed * direction;

        life--;

        if (life <= 0)
            deactivate();
    }

    public int getBurnDamage() {
        return 5;
    }

    public int getBurnTime() {
        return 3;
    }
}