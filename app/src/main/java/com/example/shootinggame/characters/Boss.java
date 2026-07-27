package com.example.shootinggame.characters;
import com.example.shootinggame.weapons.Shotgun;
import com.example.shootinggame.weapons.RocketLauncher;
import android.graphics.Rect;

import com.example.shootinggame.weapons.Pistol;
import com.example.shootinggame.weapons.Weapon;

public class Boss extends Character {

    private int speed;
    private int maxHealth;
    private BossMood currentMood;
    private boolean facingRight = false;

    // Boss Phase
    private BossPhase currentPhase;

    // Boss weapon
    private Weapon currentWeapon;
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setPhase(BossPhase phase) {
        this.currentPhase = phase;
    }

    public BossPhase getCurrentPhase() {
        return currentPhase;
    }

    public void setMood(BossMood mood) {
        this.currentMood = mood;
    }

    public BossMood getCurrentMood() {
        return currentMood;
    }

    public void setFireDelay(long delay) {
        this.fireDelay = delay;
    }

    public long getFireDelay() {
        return fireDelay;
    }
    // Shooting
    private long lastFireTime = 0;
    private long fireDelay = 1500;

    // Prevents phase animation from repeating
    private boolean phase2Triggered = false;
    private boolean phase3Triggered = false;

    public Boss(int x, int y) {

        super(x, y, 300);

        maxHealth = 300;
        damage = 25;

        currentPhase = BossPhase.PHASE1;
        currentMood = BossMood.CALM;

        currentWeapon = new Pistol();

        applyPhaseStats();
    }

    // -------------------------
    // Damage
    // -------------------------

    public void takeDamage(int amount) {

        health -= amount;

        if (health < 0)
            health = 0;

        updatePhase();
    }

    // -------------------------
    // Phase System
    // -------------------------

    private void updatePhase() {

        BossPhase oldPhase = currentPhase;

        if (health > maxHealth * 0.7f) {

            currentPhase = BossPhase.PHASE1;

        }
        else if (health > maxHealth * 0.4f) {

            currentPhase = BossPhase.PHASE2;

        }
        else {

            currentPhase = BossPhase.PHASE3;
        }

        if (oldPhase != currentPhase) {

            applyPhaseStats();

            if (currentPhase == BossPhase.PHASE2 && !phase2Triggered) {

                phase2Triggered = true;

                // Tomorrow:
                // Show "Boss is getting angry!"
            }

            if (currentPhase == BossPhase.PHASE3 && !phase3Triggered) {

                phase3Triggered = true;

                // Tomorrow:
                // Rage mode animation
            }
        }
        switch (currentPhase) {

            case PHASE1:
                currentMood = BossMood.CALM;
                break;

            case PHASE2:
                currentMood = BossMood.AGGRESSIVE;
                break;

            case PHASE3:
                currentMood = BossMood.ENRAGED;
                break;
        }
    }

    private void applyPhaseStats() {

        switch (currentPhase) {

            case PHASE1:

                speed = 2;
                damage = 25;
                fireDelay = 1500;
                currentWeapon = new Pistol();
                break;

            case PHASE2:

                speed = 4;
                damage = 35;
                fireDelay = 1000;
                currentWeapon = new Shotgun();
                break;

            case PHASE3:

                speed = 6;
                damage = 45;
                fireDelay = 600;
                currentWeapon = new RocketLauncher();
                break;
        }
    }

    // -------------------------
    // Movement
    // -------------------------

    public void moveTowardsPlayer(int playerX) {

        int distance = Math.abs(playerX - x);

        if (currentMood == BossMood.CALM) {

            // Stay about 300 pixels away
            if (distance > 300) {

                if (playerX > x) {
                    x += speed;
                    facingRight = true;
                } else {
                    x -= speed;
                    facingRight = false;
                }

            }
        }

        else if (currentMood == BossMood.AGGRESSIVE) {

            // Stay about 200 pixels away
            if (distance > 200) {

                if (playerX > x) {
                    x += speed;
                    facingRight = true;
                } else {
                    x -= speed;
                    facingRight = false;
                }

            }
        }

        else {

            // Rage Mode - Always chase
            if (playerX > x) {
                x += speed;
                facingRight = true;
            } else {
                x -= speed;
                facingRight = false;
            }

        }
    }


    // -------------------------
    // Weapon
    // -------------------------

    public Weapon getWeapon() {
        return currentWeapon;
    }

    public void setWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    // -------------------------
    // Shooting
    // -------------------------

    public boolean canShoot() {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastFireTime >= fireDelay) {

            lastFireTime = currentTime;

            return true;
        }

        return false;
    }

    // -------------------------
    // Getters
    // -------------------------



    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isFacingRight() {
        return facingRight;
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