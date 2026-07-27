package com.example.shootinggame.characters;

import com.example.shootinggame.bullets.Bullet;
import java.util.ArrayList;
import java.util.Random;

public class BossAI {
    private int burstShots = 0;
    private long lastMoodChange = 0;
    private long moodInterval = 4000;
    private BossAction currentAction = BossAction.IDLE;

    private long lastThinkTime = 0;
    private final long THINK_INTERVAL = 3000;
    private long burstDelay = 150;
    private long lastBurstShot = 0;
    public void update(Boss boss, Player player, ArrayList<Bullet> bossBullets) {

        updatePhase(boss);
        updateMood(boss);
        think(boss);
        updateMovement(boss, player);
        updateWeapon(boss);
        updateAttack(boss, player, bossBullets);

    }

    private void updatePhase(Boss boss) {

    }

    private void updateMood(Boss boss) {

    }

    private void updateWeapon(Boss boss) {

    }

    private void updateAttack(Boss boss, Player player, ArrayList<Bullet> bossBullets) {

        long currentTime = System.currentTimeMillis();

        // -------- Phase 1 --------
        if (boss.getCurrentPhase() == BossPhase.PHASE1) {

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

        // -------- Phase 2 --------
        else if (boss.getCurrentPhase() == BossPhase.PHASE2) {

            if (burstShots == 0 && boss.canShoot()) {

                burstShots = 3;
                lastBurstShot = currentTime;

            }

            if (burstShots > 0 &&
                    currentTime - lastBurstShot >= burstDelay) {

                bossBullets.addAll(
                        boss.getWeapon().fire(
                                boss.getX(),
                                boss.getY(),
                                boss.isFacingRight()
                        )
                );

                burstShots--;

                lastBurstShot = currentTime;
            }

        }

        // -------- Phase 3 --------
        else {

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

    public void updateMovement(Boss boss, Player player) {

        int distance = Math.abs(player.getX() - boss.getX());

        if (boss.getCurrentMood() == BossMood.CALM) {

            if (distance > 350) {

                boss.moveTowardsPlayer(player.getX());

            }

        }

        else if (boss.getCurrentMood() == BossMood.AGGRESSIVE) {

            if (distance > 220) {

                boss.moveTowardsPlayer(player.getX());

            }

        }

        else {

            boss.moveTowardsPlayer(player.getX());

        }
    }
    private void think(Boss boss) {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastThinkTime < THINK_INTERVAL)
            return;

        lastThinkTime = currentTime;

        Random random = new Random();

        if (boss.getCurrentMood() == BossMood.CALM) {

            currentAction = random.nextBoolean()
                    ? BossAction.SHOOT
                    : BossAction.CHASE;

        } else if (boss.getCurrentMood() == BossMood.AGGRESSIVE) {

            int choice = random.nextInt(3);

            switch (choice) {
                case 0:
                    currentAction = BossAction.BURST_FIRE;
                    break;
                case 1:
                    currentAction = BossAction.CHASE;
                    break;
                default:
                    currentAction = BossAction.SHOOT;
                    break;
            }

        } else if (boss.getCurrentMood() == BossMood.DEFENSIVE) {

            currentAction = BossAction.RETREAT;

        } else {

            currentAction = BossAction.DASH;
        }
        System.out.println("Boss Action: " + currentAction);
    }

}
