package com.example.shootinggame.characters;

public class Boss extends Character {

    private int speed;
    private int phase;
    private int maxHealth;

    public Boss(int x, int y) {
        super(x, y, 300);   // Boss starts with 300 HP

        this.maxHealth = 300;
        this.damage = 25;
        this.speed = 2;
        this.phase = 1;
    }

    private boolean facingRight = false;

    public boolean isFacingRight() {
        return facingRight;
    }
    public void takeDamage(int amount) {
        health -= amount;

        if (health < 0)
            health = 0;

        updatePhase();
    }

    private void updatePhase() {

        if (health > 200) {
            phase = 1;
        } else if (health > 100) {
            phase = 2;
        } else {
            phase = 3;
        }

    }

    public void moveTowardsPlayer(int playerX) {

        if (playerX > x) {
            x += speed;
            facingRight = true;
        } else if (playerX < x) {
            x -= speed;
            facingRight = false;
        }

    }

    public int getPhase() {
        return phase;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isDead() {
        return health <= 0;
    }

}

