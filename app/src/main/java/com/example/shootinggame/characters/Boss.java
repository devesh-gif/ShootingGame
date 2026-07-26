package com.example.shootinggame.characters;
import android.graphics.Rect;
import com.example.shootinggame.weapons.Pistol;
import com.example.shootinggame.weapons.Weapon;

public class Boss extends Character {

    private int speed;
    private int phase;
    private int maxHealth;

    private boolean facingRight = false;

    // Boss weapon
    private Weapon currentWeapon;

    // Shooting cooldown
    private long lastFireTime = 0;
    private long fireDelay = 800;

    public Boss(int x, int y) {
        super(x, y, 300);

        maxHealth = 300;
        damage = 25;
        speed = 2;
        phase = 1;

        // Boss starts with a pistol
        currentWeapon = new Pistol();
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
        }
        else if (health > 100) {
            phase = 2;
        }
        else {
            phase = 3;
        }
    }

    public void moveTowardsPlayer(int playerX) {

        if (playerX > x) {
            x += speed;
            facingRight = true;
        }
        else if (playerX < x) {
            x -= speed;
            facingRight = false;
        }
    }

    // -------------------------
    // Weapon Methods
    // -------------------------

    public Weapon getWeapon() {
        return currentWeapon;
    }

    public void setWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    public boolean canShoot() {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastFireTime >= fireDelay) {

            lastFireTime = currentTime;
            return true;
        }

        return false;
    }

    public void setFireDelay(long delay) {
        fireDelay = delay;
    }

    // -------------------------

    public boolean isFacingRight() {
        return facingRight;
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
    public Rect getHitbox() {

        return new Rect(
                x + 45,
                y + 15,
                x + 135,
                y + 170
        );
    }

}