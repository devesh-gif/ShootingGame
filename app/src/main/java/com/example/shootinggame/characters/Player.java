package com.example.shootinggame.characters;
import android.graphics.Rect;
import com.example.shootinggame.weapons.Pistol;
import com.example.shootinggame.weapons.Weapon;

public class Player extends Character {

    private int speed;
    private boolean facingRight = true;

    private int velocityY = 0;
    private boolean isJumping = false;

    private final int gravity = 1;

    // NEW
    private Weapon currentWeapon;
    public Rect getHitbox() {

        return new Rect(
                x + 45,
                y + 15,
                x + 135,
                y + 170
        );
    }

    public Player(int x, int y) {

        super(x, y, 100);

        damage = 20;
        speed = 15;

        // Player starts with a pistol
        currentWeapon = new Pistol();
    }

    public Weapon getWeapon() {
        return currentWeapon;
    }

    public void setWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void jump() {

        if (!isJumping) {

            velocityY = -22;
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

        if (health < 0)
            health = 0;
    }

}