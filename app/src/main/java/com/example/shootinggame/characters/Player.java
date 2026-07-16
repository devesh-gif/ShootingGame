package com.example.shootinggame.characters;

public class Player extends Character {
    private int speed;
    private boolean facingRight = true;
    private int velocityY = 0;
    private boolean isJumping = false;
    private final int gravity = 1;

    public Player(int x, int y) {
        super(x, y, 100);
        this.damage = 20;
        speed = 15;
    }
    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }
    public void move() {
        x += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void jump() {
        if (!isJumping) {
            velocityY = -15;
            isJumping = true;
        }
    }

    public void update() {
        y += velocityY;
        velocityY += gravity;

        if (y >= 730) {
            y = 730;
            velocityY = 0;
            isJumping = false;
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }
}
