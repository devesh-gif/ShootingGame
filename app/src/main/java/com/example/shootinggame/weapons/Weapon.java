package com.example.shootinggame.weapons;

import com.example.shootinggame.bullets.Bullet;

import java.util.ArrayList;

public abstract class Weapon {

    protected String name;
    protected int damage;
    protected int fireRate;
    protected int bulletSpeed;

    public Weapon(String name, int damage, int fireRate, int bulletSpeed) {
        this.name = name;
        this.damage = damage;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
    }

    // Every weapon returns one or more bullets
    public abstract ArrayList<Bullet> fire(int x, int y, boolean facingRight);

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getFireRate() {
        return fireRate;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }
}