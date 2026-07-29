package com.example.shootinggame.characters;

import com.example.shootinggame.bullets.Bullet;
import com.example.shootinggame.weapons.RocketLauncher;
import com.example.shootinggame.weapons.SMG;
import com.example.shootinggame.weapons.Shotgun;
import com.example.shootinggame.weapons.Sniper;

import java.util.ArrayList;

public class BossAI {
    private BossMemory memory = new BossMemory();

    public void update(Boss boss, Player player, ArrayList<Bullet> bossBullets) {

        chooseMood(boss, player);

        chooseWeapon(boss, player);

        updateMovement(boss, player);

        updateAttack(boss, bossBullets);
        int distance = Math.abs(player.getX() - boss.getX());

        memory.observePlayer(distance);
    }

    // -------------------------
    // Mood depends on distance
    // -------------------------

    private void chooseMood(Boss boss, Player player) {

        int distance = Math.abs(player.getX() - boss.getX());

        if (distance > 500) {

            boss.setCurrentMood(BossMood.CALM);

        }
        else if (distance > 200) {

            boss.setCurrentMood(BossMood.AGGRESSIVE);

        }
        else {

            boss.setCurrentMood(BossMood.ENRAGED);

        }
        System.out.println("Distance: " + distance);
        System.out.println("Mood: " + boss.getCurrentMood());
    }

    // -------------------------
    // Weapon Selection
    // -------------------------

    private void chooseWeapon(Boss boss, Player player) {

        switch (boss.getCurrentPhase()) {

            // -----------------
            // Phase 1
            // -----------------

            case PHASE1:

                boss.setWeapon(new SMG());

                break;

            // -----------------
            // Phase 2
            // -----------------

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

            // -----------------
            // Phase 3
            // -----------------

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

        String weapon = boss.getWeapon().getName();

        // ---------- Shotgun ----------

        if (weapon.equals("Shotgun")) {

            if (distance > 150) {

                boss.moveTowardsPlayer(player.getX());

            }

        }

        // ---------- SMG ----------

        else if (weapon.equals("SMG")) {

            if (distance > 300) {

                boss.moveTowardsPlayer(player.getX());

            }

        }

        // ---------- Sniper ----------

        else if (weapon.equals("Sniper")) {

            if (distance < 500) {

                boss.moveAwayFromPlayer(player.getX());

            }

        }

        // ---------- Rocket ----------

        else if (weapon.equals("Rocket Launcher")) {

            if (distance > 350) {

                boss.moveTowardsPlayer(player.getX());

            }

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

        }
    }
}