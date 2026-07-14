package com.example.shootinggame.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.shootinggame.Joystick;
import com.example.shootinggame.R;
import com.example.shootinggame.characters.Enemy;
import com.example.shootinggame.characters.Player;
import com.example.shootinggame.objects.Bullet;
public class GameView extends SurfaceView implements Runnable {
    private static final int MENU = 0;
    private static final int SELECT_BACKGROUND = 1;
    private static final int GAME = 2;
    private static final int PLAYER_SIZE = 180;
    private static final int ENEMY_SIZE = 280;

    private int gameState = MENU;
    private boolean jumpPressed = false;
    Thread thread;
    private Rect lightForestButton;
    private Rect darkForestButton;
    boolean isPlaying = false;
    boolean movingLeft = false;
    boolean movingRight = false;

    private int bgX = 0;
    private int bgSpeed = 4;

    SurfaceHolder holder;
    Joystick joystick;
    Bitmap idlePlayer;

    Rect shootButton;
    Rect jumpButton;
    Bullet bullet;

    Bitmap[] playerFrames = new Bitmap[6];
    Bitmap[] enemyFrames = new Bitmap[6];

    int playerFrame = 0;
    int enemyFrame = 0;
    int frameCounter = 0;

    Player player;
    Enemy enemy;
    float joystickX = 170;
    float joystickY = 0;
    Bitmap forestlightBg;
    Bitmap forestdarkBg;


    Bitmap currentBg;
    public GameView(Context context) {
        super(context);

        holder = getHolder();

        playerFrames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
        playerFrames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
        playerFrames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.player3);
        playerFrames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.player4);
        playerFrames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.player5);
        playerFrames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.player6);
        idlePlayer = BitmapFactory.decodeResource(getResources(), R.drawable.idle);

        enemyFrames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1);
        enemyFrames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2);
        enemyFrames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.enemy3);
        enemyFrames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.enemy4);
        enemyFrames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.enemy5);
        enemyFrames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.enemy6);

        player = new Player(100, 560);
        enemy = new Enemy(1500, 700);
        bullet = new Bullet();
        joystick = new Joystick(
                170,
                getResources().getDisplayMetrics().heightPixels - 170
        );
        
        shootButton = new Rect(
                getResources().getDisplayMetrics().widthPixels - 250,
                getResources().getDisplayMetrics().heightPixels - 250,
                getResources().getDisplayMetrics().widthPixels - 50,
                getResources().getDisplayMetrics().heightPixels - 50
        );

        forestlightBg = BitmapFactory.decodeResource(getResources(), R.drawable.forestlightbg);
        forestdarkBg = BitmapFactory.decodeResource(getResources(), R.drawable.forestdarkbg);
        forestlightBg.setHasAlpha(false);
        forestdarkBg.setHasAlpha(false);
        currentBg = forestlightBg;
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            if (!holder.getSurface().isValid())
                continue;
            Canvas canvas = holder.lockCanvas();
            drawGame(canvas);
            holder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(16);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (gameState != GAME) {
            return;
        }
        player.update();

        if (joystick.isLeft()) {
            player.moveLeft();
            bgX -= bgSpeed;
        }

        if (joystick.isRight()) {
            player.moveRight();
            bgX -= bgSpeed;
        }

        if (bgX <= -currentBg.getWidth()) {
            bgX = 0;
        }

        enemy.move();
        bullet.move();

        frameCounter++;

        if (frameCounter > 6) {

            // Animate player only when moving
            if (joystick.isLeft() || joystick.isRight()) {
                playerFrame = (playerFrame + 1) % 6;
            } else {
                // Standing image
                playerFrame = 0;
            }

            // Enemy always walks
            enemyFrame = (enemyFrame + 1) % 6;

            frameCounter = 0;
        }

        // Enemy respawn
        if (enemy.getX() < -500) {
            enemy = new Enemy(2000, 700);
        }

        // Bullet collision
        if (bullet.active &&
                bullet.getX() > enemy.getX() &&
                bullet.getX() < enemy.getX() + ENEMY_SIZE &&
                bullet.getY() > enemy.getY() &&
                bullet.getY() < enemy.getY() + ENEMY_SIZE) {

            enemy.takeDamage(player.getDamage());
            bullet.active = false;
            if (enemy.getHealth() <= 0) {

                // Respawn enemy
                enemy = new Enemy(2000, 700);

            }
        }
    }

    private void drawGame(Canvas canvas) {
        if (gameState == MENU) {
            drawMenu(canvas);
            return;
        }
        if (gameState == SELECT_BACKGROUND) {
            drawBackgroundSelection(canvas);
            return;
        }

// Game
        drawGameScreen(canvas);
        canvas.drawColor(Color.BLACK);
        Rect dst1 = new Rect(
                bgX,
                0,
                bgX + canvas.getWidth(),
                canvas.getHeight()
        );

        Rect dst2 = new Rect(
                bgX + canvas.getWidth(),
                0,
                bgX + canvas.getWidth() * 2,
                canvas.getHeight()
        );

        Paint bgPaint = new Paint();
        bgPaint.setFilterBitmap(true);
        bgPaint.setAntiAlias(true);

        canvas.drawBitmap(currentBg, null, dst1, bgPaint);
        canvas.drawBitmap(currentBg, null, dst2, bgPaint);
        Paint borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(3);

        Paint barBgPaint = new Paint();
        barBgPaint.setColor(Color.GRAY);

        Paint healthPaint = new Paint();
        healthPaint.setColor(Color.RED);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        textPaint.setFakeBoldText(true);

        int barWidth = 300;
        int barHeight = 30;
        canvas.drawText("PLAYER",40,50,textPaint);
        // ENEMY HEALTH BAR
        int enemyBarWidth = 300;
        int enemyLeft = canvas.getWidth() - 340;

        canvas.drawText(
                "ENEMY",
                enemyLeft + 100,
                50,
                textPaint
        );

        canvas.drawRect(
                enemyLeft,
                70,
                enemyLeft + enemyBarWidth,
                100,
                barBgPaint
        );

        canvas.drawRect(
                enemyLeft,
                70,
                enemyLeft + (enemy.getHealth() * enemyBarWidth / enemy.getMaxHealth()),
                100,
                healthPaint
        );

        canvas.drawRect(
                enemyLeft,
                70,
                enemyLeft + enemyBarWidth,
                100,
                borderPaint
        );

        canvas.drawText(
                enemy.getHealth() + "/" + enemy.getMaxHealth(),
                enemyLeft + 90,
                130,
                textPaint
        );

        canvas.drawRect(
                40,
                70,
                40+barWidth,
                70+barHeight,
                barBgPaint);

        canvas.drawRect(
                40,
                70,
                40+(player.getHealth()*barWidth/player.getMaxHealth()),
                70+barHeight,
                healthPaint);

        canvas.drawRect(
                40,
                70,
                40+barWidth,
                70+barHeight,
                borderPaint);

        canvas.drawText(
                player.getHealth()+"/"+player.getMaxHealth(),
                120,
                130,
                textPaint);

        Bitmap playerBitmap = Bitmap.createScaledBitmap(
                playerFrames[playerFrame],
                180,
                180,
                false
        );
        Bitmap idleBitmap = Bitmap.createScaledBitmap(
                idlePlayer,
                180,
                180,
                false
        );

        Bitmap enemyBitmap = Bitmap.createScaledBitmap(
                enemyFrames[enemyFrame],
                280,
                280,
                false
        );

        if (joystick.isLeft() || joystick.isRight()) {

            canvas.drawBitmap(
                    playerBitmap,
                    player.getX(),
                    player.getY(),
                    null
            );

        } else {

            canvas.drawBitmap(
                    idleBitmap,
                    player.getX(),
                    player.getY(),
                    null
            );
        }

        canvas.drawBitmap(
                enemyBitmap,
                enemy.getX(),
                enemy.getY(),
                null
        );

        Paint bulletPaint = new Paint();
        bulletPaint.setColor(Color.YELLOW);

        if (bullet.active) {
            canvas.drawCircle(
                    bullet.getX(),
                    bullet.getY(),
                    12,
                    bulletPaint
            );
        }
        joystick.draw(canvas);


        Paint firePaint = new Paint();
        firePaint.setColor(Color.RED);

        int fireX = canvas.getWidth() - 150;
        int fireY = canvas.getHeight() - 150;

        canvas.drawCircle(fireX, fireY, 80, firePaint);

        firePaint.setColor(Color.WHITE);
        firePaint.setTextSize(40);

        canvas.drawText(
                "FIRE",
                fireX - 45,
                fireY + 15,
                firePaint
        );
        jumpButton = new Rect(
                canvas.getWidth() - 420,
                canvas.getHeight() - 230,
                canvas.getWidth() - 270,
                canvas.getHeight() - 80
        );

        Paint jumpPaint = new Paint();
        jumpPaint.setColor(Color.BLUE);

        canvas.drawOval(
                new android.graphics.RectF(jumpButton),
                jumpPaint
        );

        jumpPaint.setColor(Color.WHITE);
        jumpPaint.setTextSize(40);
        canvas.drawText(
                "JUMP",
                jumpButton.left + 15,
                jumpButton.centerY() + 15,
                jumpPaint
        );
    }

    private void drawMenu(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("SHOOTING GAME", canvas.getWidth() / 2, canvas.getHeight() / 2 - 100, paint);
        paint.setTextSize(50);
        canvas.drawText("Tap to Play", canvas.getWidth() / 2, canvas.getHeight() / 2 + 50, paint);
    }

    private void drawBackgroundSelection(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        Paint titlePaint = new Paint();
        titlePaint.setColor(Color.WHITE);
        titlePaint.setTextSize(80);
        titlePaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(
                "SELECT BACKGROUND",
                canvas.getWidth() / 2,
                150,
                titlePaint
        );

        lightForestButton = new Rect(
                120,
                250,
                canvas.getWidth() - 120,
                500
        );

        darkForestButton = new Rect(
                120,
                600,
                canvas.getWidth() - 120,
                850
        );

        canvas.drawBitmap(
                Bitmap.createScaledBitmap(
                        forestlightBg,
                        lightForestButton.width(),
                        lightForestButton.height(),
                        true
                ),
                lightForestButton.left,
                lightForestButton.top,
                null
        );

        canvas.drawBitmap(
                Bitmap.createScaledBitmap(
                        forestdarkBg,
                        darkForestButton.width(),
                        darkForestButton.height(),
                        true
                ),
                darkForestButton.left,
                darkForestButton.top,
                null
        );

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(
                "Light Forest",
                lightForestButton.centerX(),
                lightForestButton.bottom + 60,
                textPaint
        );

        canvas.drawText(
                "Dark Forest",
                darkForestButton.centerX(),
                darkForestButton.bottom + 60,
                textPaint
        );// Implementation for background selection
    }

    private void drawGameScreen(Canvas canvas) {
        // Implementation for game screen
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameState == MENU) {
            gameState = SELECT_BACKGROUND;
            return true;
        }

        float x = event.getX();
        float y = event.getY();
        if (gameState == SELECT_BACKGROUND &&
                event.getAction() == MotionEvent.ACTION_DOWN) {

            if (lightForestButton.contains((int)x, (int)y)) {

                currentBg = forestlightBg;
                gameState = GAME;
                return true;
            }

            if (darkForestButton.contains((int)x, (int)y)) {

                currentBg = forestdarkBg;
                gameState = GAME;
                return true;
            }
        }

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:

                // If touching joystick
                if (joystick.contains(x, y)) {
                    joystick.update(x, y);
                } else {
                    // Touch anywhere else → center the joystick
                    joystick.reset();
                }

                // FIRE button
                int dx = (int)x - (getWidth() - 150);
                int dy = (int)y - (getHeight() - 150);

                if (dx * dx + dy * dy <= 80 * 80) {
                    if (!bullet.active) {
                        bullet.shoot(player.getX() + 130, player.getY() + 90);
                    }
                }

                // JUMP button
                if (jumpButton.contains((int)x, (int)y)) {
                    player.jump();
                }

                break;

            case MotionEvent.ACTION_MOVE:

                if (joystick.contains(x, y)) {
                    joystick.update(x, y);
                }

                break;

            case MotionEvent.ACTION_UP:

                joystick.reset();
                jumpPressed = false;

                invalidate();

                break;
        }

        return true;
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            if (thread != null) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
