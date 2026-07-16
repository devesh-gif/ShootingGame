package com.example.shootinggame.objects;

public class Bullet {

    private int x, y;
    private int speed;
    private int direction;

    public boolean active;

    public Bullet() {
        speed = 20;        // Bullet speed
        direction = 1;     // 1 = Right, -1 = Left
        active = false;
    }

    public void shoot(int startX, int startY, int direction) {

        this.x = startX;
        this.y = startY;
        this.direction = direction;

        active = true;
    }

    public void move() {

        if (!active)
            return;

        x += speed * direction;

        if (x > 3500 || x < -500) {
            active = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}