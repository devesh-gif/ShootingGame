
package com.example.shootinggame.characters;

public class BossMemory {

    private int closeCount;
    private int mediumCount;
    private int farCount;

    public void observePlayer(int distance) {

        if (distance < 200) {

            closeCount++;

        } else if (distance < 500) {

            mediumCount++;

        } else {

            farCount++;

        }
    }

    public int getCloseCount() {
        return closeCount;
    }

    public int getMediumCount() {
        return mediumCount;
    }

    public int getFarCount() {
        return farCount;
    }

    public void reset() {

        closeCount = 0;
        mediumCount = 0;
        farCount = 0;
    }
}