package com.example.shootinggame.characters;

public class Enemy extends Character {
    private int speed;

    public Enemy(int x, int y) {
        super(x, y, 100);
        this.damage = 15;
        this.speed = 3;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public void move() {
        x -= speed;
    }
}
