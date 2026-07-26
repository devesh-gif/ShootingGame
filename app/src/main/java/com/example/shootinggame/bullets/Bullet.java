package com.example.shootinggame.bullets;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bullet {

    protected float x, y;
    protected int damage;
    protected int speed;
    protected int direction;
    protected boolean active;

    protected Paint paint;

    public Bullet(int damage, int speed) {
        this.damage = damage;
        this.speed = speed;

        active = false;

        paint = new Paint();
        paint.setColor(Color.YELLOW);
    }

    public void shoot(float startX, float startY, boolean facingRight) {

        x = startX;
        y = startY;

        direction = facingRight ? 1 : -1;

        active = true;
    }

    public void move() {

        if (!active)
            return;

        x += speed * direction;
    }

    public void draw(Canvas canvas) {

        if (active)
            canvas.drawCircle(x, y, 10, paint);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}