package com.example.shootinggame.objects;

public class Bullet {
    private int x, y, speed;
    public boolean active;

    public Bullet() {
        speed = 8;
        active = false;
    }

    public void shoot(int startX, int startY) {
        x = startX;
        y = startY;
        active = true;
    }

    public void move() {
        if (active) {
            x += speed;
            if (x > 3000) active = false;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
}