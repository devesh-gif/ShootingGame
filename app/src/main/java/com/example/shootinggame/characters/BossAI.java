package com.example.shootinggame.characters;

import android.util.Log;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.weapons.RocketLauncher;
import com.example.shootinggame.weapons.SMG;
import com.example.shootinggame.weapons.Shotgun;
import com.example.shootinggame.weapons.Sniper;
import com.example.shootinggame.game.SoundManager;

import java.util.ArrayList;

public class BossAI {
    private BossMemory memory = new BossMemory();

    private boolean walking = true;

    private long lastModeChange = System.currentTimeMillis();

    private final long WALK_TIME = 3000;

    private final long SHOOT_TIME = 2000;

    private long lastWeaponChange = 0;

    private final long WEAPON_CHANGE_DELAY = 5000;
    private SoundManager soundManager;

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }
    public void update(Boss boss,
                       Player player,
                       ArrayList<Bullet> bossBullets) {
        // Always face the player
        if (player.getX() > boss.getX()) {
            boss.setFacingRight(true);
        } else {
            boss.setFacingRight(false);
        }

        chooseMood(boss, player);

        chooseWeapon(boss, player);

        long current = System.currentTimeMillis();

        if (walking) {

            updateMovement(boss, player);

            if (current - lastModeChange >= WALK_TIME) {

                walking = false;
                lastModeChange = current;

            }

        } else {

            updateAttack(boss, bossBullets);

            if (current - lastModeChange >= SHOOT_TIME) {

                walking = true;
                lastModeChange = current;

            }
        }

        memory.observePlayer(
                Math.abs(player.getX() - boss.getX())
        );
    }

    // -------------------------
    // Mood depends on distance
    // -------------------------

    private void chooseMood(Boss boss, Player player) {

        int distance = Math.abs(player.getX() - boss.getX());

        if (distance > 500) {

            boss.setCurrentMood(BossMood.CALM);

        } else if (distance > 250) {

            boss.setCurrentMood(BossMood.AGGRESSIVE);

        } else {

            boss.setCurrentMood(BossMood.ENRAGED);

        }

    }

    // -------------------------
    // Weapon Selection
    // -------------------------

    private void chooseWeapon(Boss boss, Player player) {

        long currentTime = System.currentTimeMillis();

        // Don't change weapon too often
        if (currentTime - lastWeaponChange < WEAPON_CHANGE_DELAY)
            return;

        lastWeaponChange = currentTime;

        switch (boss.getCurrentPhase()) {

            //-------------------------
            // Phase 1
            //-------------------------

            case PHASE1:

                boss.setWeapon(new SMG());

                break;

            //-------------------------
            // Phase 2
            //-------------------------

            case PHASE2:

                switch (boss.getCurrentMood()) {

                    case CALM:

                        boss.setWeapon(new Sniper());

                        break;

                    case AGGRESSIVE:

                        boss.setWeapon(new SMG());

                        break;

                    case ENRAGED:

                        boss.setWeapon(new Shotgun());

                        break;
                }

                break;

            //-------------------------
            // Phase 3
            //-------------------------

            case PHASE3:

                switch (boss.getCurrentMood()) {

                    case CALM:

                        boss.setWeapon(new Sniper());

                        break;

                    case AGGRESSIVE:

                        boss.setWeapon(new SMG());

                        break;

                    case ENRAGED:

                        boss.setWeapon(new RocketLauncher());

                        break;
                }

                break;
        }
    }

    // -------------------------
    // Movement
    // -------------------------

    private void updateMovement(Boss boss, Player player) {


        int distance = Math.abs(player.getX() - boss.getX());
        System.out.println("Walking");
        System.out.println("Distance = " + distance);
        System.out.println("Boss X = " + boss.getX());
        System.out.println("Player X = " + player.getX());

        switch (boss.getWeapon().getName()) {

            // ---------------- SMG ----------------

            case "SMG":

                if (distance > 280) {

                    boss.moveTowardsPlayer(player.getX());

                }

                break;

            // ---------------- Shotgun ----------------

            case "Shotgun":

                if (distance > 150) {

                    boss.moveTowardsPlayer(player.getX());

                }

                break;

            // ---------------- Sniper ----------------

            case "Sniper":

                if (distance < 600) {

                    boss.moveAwayFromPlayer(player.getX());

                }

                break;

            // ---------------- Rocket Launcher ----------------

            case "Rocket Launcher":

                if (distance > 350) {

                    boss.moveTowardsPlayer(player.getX());

                } else if (distance < 200) {

                    boss.moveAwayFromPlayer(player.getX());

                }

                break;
        }

    }


    // -------------------------
    // Attack
    // -------------------------

    private void updateAttack(Boss boss,
                              ArrayList<Bullet> bossBullets) {

        if (boss.canShoot()) {


            bossBullets.addAll(
                    boss.getWeapon().fire(
                            boss.getX(),
                            boss.getY(),
                            boss.isFacingRight()

                    )
            );
            Log.d("BossAI", "Sound = " + soundManager);
            if (soundManager != null) {


                switch (boss.getWeapon().getName()) {

                    case "Pistol":
                        soundManager.playPistol();
                        break;

                    case "SMG":
                        soundManager.playSMG();
                        break;

                    case "Shotgun":
                        soundManager.playShotgun();
                        break;

                    case "Sniper":
                        soundManager.playSniper();
                        break;

                    case "Rocket Launcher":
                        soundManager.playRocket();
                        break;
                }
            }
        }
    }
    public boolean isWalking() {
        return walking;
    }
}