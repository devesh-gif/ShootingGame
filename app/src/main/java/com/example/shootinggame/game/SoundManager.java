package com.example.shootinggame.game;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.example.shootinggame.R;

public class SoundManager {

    private SoundPool soundPool;

    private int pistol;
    private int smg;
    private int shotgun;
    private int sniper;
    private int rocket;
    private int explosion;
    private int hit;
    private int victory;

    public SoundManager(Context context) {

        AudioAttributes audioAttributes =
                new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(audioAttributes)
                .build();

        pistol = soundPool.load(context, R.raw.pistol, 1);
        smg = soundPool.load(context, R.raw.smg, 1);
        shotgun = soundPool.load(context, R.raw.shotgun, 1);
        sniper = soundPool.load(context, R.raw.sniper, 1);
        rocket = soundPool.load(context, R.raw.rocket, 1);
        explosion = soundPool.load(context, R.raw.explosion, 1);
        hit = soundPool.load(context, R.raw.hit, 1);
        victory = soundPool.load(context, R.raw.victory, 1);
    }

    public void playPistol() {
        soundPool.play(pistol,1,1,1,0,1);
    }

    public void playSMG() {
        soundPool.play(smg,1,5,1,0,1);
    }

    public void playShotgun() {
        soundPool.play(shotgun,1,1,1,0,1);
    }

    public void playSniper() {
        soundPool.play(sniper,1,1,1,0,1);
    }

    public void playRocket() {
        soundPool.play(rocket,1,1,1,0,1);
    }

    public void playExplosion() {
        soundPool.play(explosion,1,1,1,0,1);
    }

    public void playHit() {
        soundPool.play(hit,1,1,1,0,1);
    }

    public void playVictory() {
        soundPool.play(victory,1,1,1,0,1);
    }
}