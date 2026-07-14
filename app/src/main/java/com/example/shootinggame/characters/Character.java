package com.example.shootinggame.characters;

public abstract class Character {
    protected int x, y, health, maxHealth, damage;

    public Character(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.maxHealth = health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
