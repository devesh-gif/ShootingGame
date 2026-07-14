package com.example.shootinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private float centerX;
    private float centerY;

    private float knobX;
    private float knobY;

    private float radius = 100;
    private float knobRadius = 45;

    public Joystick(float x, float y) {
        centerX = x;
        centerY = y;

        knobX = x;
        knobY = y;
    }

    public void draw(Canvas canvas) {

        Paint outer = new Paint();
        outer.setColor(Color.DKGRAY);

        Paint inner = new Paint();
        inner.setColor(Color.LTGRAY);

        // Outer base
        canvas.drawCircle(centerX, centerY, radius, outer);

        // Clamp knob position
        float dx = knobX - centerX;
        float dy = knobY - centerY;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance > radius) {
            dx = dx / distance * radius;
            dy = dy / distance * radius;

            knobX = centerX + dx;
            knobY = centerY + dy;
        }

        // Draw knob
        canvas.drawCircle(knobX, knobY, knobRadius, inner);
    }

    public void update(float x, float y) {

        float dx = x - centerX;
        float dy = y - centerY;

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if(distance > radius){

            dx = dx / distance * radius;
            dy = dy / distance * radius;
        }

        knobX = centerX + dx;
        knobY = centerY + dy;
    }

    public void reset() {
        knobX = centerX;
        knobY = centerY;
    }

    public boolean isLeft() {
        return knobX < centerX - 20;
    }

    public boolean isRight() {
        return knobX > centerX + 20;
    }

    public boolean isUp() {
        return knobY < centerY - 20;
    }

    public boolean contains(float x,float y){

        float dx=x-centerX;
        float dy=y-centerY;

        return dx*dx+dy*dy<=radius*radius;
    }
}
