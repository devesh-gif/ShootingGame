package com.example.shootinggame.bullets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Matrix;

public class Bullet {

    protected float x, y;
    protected float startX;
    protected int range = 300;
    protected int damage;
    protected int speed;
    protected int direction;
    protected boolean active;
    protected Bitmap bulletImage;

    protected Paint paint;
    private long fireTime;


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
        this.startX = startX;
        direction = facingRight ? 1 : -1;

        active = true;
        fireTime = System.currentTimeMillis();
    }

    public void move() {

        if (!active)
            return;
        x += speed * direction;
        if (Math.abs(x - startX) >= range) {
            active = false;
        }
    }

    public void setImage(Bitmap image) {

        bulletImage = image;

    }
    public void setRange(int range) {

        this.range = range;

    }

    public void draw(Canvas canvas) {

        if (!active)
            return;

        Matrix matrix = new Matrix();

        if (direction == 1) {

            // Right
            matrix.postTranslate(x, y);

        } else {

            // Left (mirror)
            matrix.preScale(-1, 1);
            matrix.postTranslate(x + bulletImage.getWidth(), y);

        }

        canvas.drawBitmap(
                bulletImage,
                matrix,
                null
        );
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