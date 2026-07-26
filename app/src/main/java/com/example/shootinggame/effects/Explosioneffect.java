package com.example.shootinggame.effects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Explosioneffect {
    private float x, y;
    private float currentRadius;
    private float maxRadius;
    private boolean alive;
    private Paint paint;

    public Explosioneffect(float x, float y, int radius) {
        this.x = x;
        this.y = y;
        this.maxRadius = radius;
        this.currentRadius = 0;
        this.alive = true;
        this.paint = new Paint();
        this.paint.setColor(Color.YELLOW);
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void update() {
        if (!alive) return;
        
        currentRadius += 5; // Expand speed
        if (currentRadius >= maxRadius) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void draw(Canvas canvas) {
        if (!alive && currentRadius == 0) return;

        // Fade out effect
        int alpha = (int) (255 * (1 - currentRadius / maxRadius));
        if (alpha < 0) alpha = 0;
        paint.setAlpha(alpha);
        
        canvas.drawCircle(x, y, currentRadius, paint);
    }
}
