package com.example.crossyroadgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {
    private final String name;
    private final int spriteId;
    private final int altSpriteId;
    private final int rows;
    private final int cols;
    private final int tileSize;



    private final String difficulty;

    private int score;



    private int previousVertical;

    private double x;
    private int y;
    private int lives;
    private int speed;
    private int direction;
    private int movingDirectionX;
    private int movingDirectionY;
    private long lastDrawNanoTime = -1;
    private Bitmap image;

    private boolean win;

    private String endText;

    private boolean onLog;



    public Player(String name, int tileSize, int rows,
                  int cols, int lives, int[] playerSpriteIdArray) {
        this.name = name;
        this.tileSize = tileSize;
        this.rows = rows;
        this.cols = cols;
        this.spriteId = playerSpriteIdArray[0];
        this.altSpriteId = playerSpriteIdArray[1];
        //this.spriteId = R.drawable.camel;
        x = cols / 2;
        y = rows - 1;
        this.lives = lives;
        this.score = 0;
        this.previousVertical = 10;
        this.difficulty = findDifficulty();
        this.win = false;
        this.endText = "Game Over";
        this.onLog = false;

    }

    public void draw(Canvas canvas, Bitmap playerLeftBitMap, Bitmap playerRightBitMap) {
        if (direction == 1) {
            canvas.drawBitmap(playerLeftBitMap, (int) x * tileSize, y * tileSize, null);
        } else {
            canvas.drawBitmap(playerRightBitMap, (int) x * tileSize, y * tileSize, null);
        }
    }
    public void update(ControlPad controlPad) {
        x += controlPad.getDirectionX();
        y += controlPad.getDirectionY();


    }
    public int[] getXYPixels() {
        return new int[] {(int) x * tileSize, y * tileSize};
    }
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public int getSpriteId() {
        return spriteId;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getLives() {
        return lives;
    }

    public void moveUp() {
        if (y > 0) {
            y -= 1;

        } else {
            this.win = true;
            endText = "Congrats! You win";
        }

        if (previousVertical > y) {
            if (y == 8) {
                score += 20;
            } else if (y == 7) {
                score += 15;
            } else if (y == 6) {
                score += 50;
            } else if (y == 5) {
                score += 10;
            } else if (y == 0) {
                score += 100;
            } else {
                score += 5;
            }


            previousVertical = y;
        }

    }
    public void moveDown() {
        if (y < rows - 1) {
            y += 1;
        }
    }

    public void logCollision(Log log) {
        if (log.getSpeed() == 2) {
            this.onLog = true;
        }
    }

    public boolean getOnLog() {
        return this.onLog;
    }

    public boolean getWin() {
        return this.win;
    }
    public String getEndText() {
        return this.endText;
    }
    public void moveLeft() {
        if (x > 0) {
            x -= 1;
        }
        direction = 1;
    }
    public void moveLeftSlowly() {
        if (x > 0) {
            x = x - 0.1;
        }
        direction = 1;
    }
    public void moveRightSlowly() {
        if (x < cols - 1) {
            x = x + 0.1;
        }
        direction = 0;
    }
    public void moveRight() {
        if (x < cols - 1) {
            x += 1;
        }
        direction = 0;
    }

    public int getDirectionX() {
        return direction;
    }
    public String getDifficulty() {
        return difficulty;
    }

    public String findDifficulty() {
        switch (6 - lives) {
        case 1:
            return "EASY";

        case 3:
            return "MEDIUM";

        case 5:
            return "HARD";

        default:
            return null;
        }

    }

    public int getTileSize() {
        return this.tileSize;
    }


    public void loseLife() {
        lives--;
        x = cols / 2;
        y = rows - 1;
        if (lives > 0) {
            score = 0;
        }
        previousVertical = 10;
    }

    public void rideLogLeft(Log log) {
        moveLeftSlowly();
        if (x == 0) {
            loseLife();
        }
    }
    public void rideLogRight(Log log) {
        moveRightSlowly();
        if (x == cols - 1) {
            loseLife();
        }
    }
}
