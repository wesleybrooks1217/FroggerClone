package com.example.crossyroadgame;


import android.content.Context;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ControlPad {
    private int x;
    private int y;
    private int tileSize;
    private int halfTileSize;
    private boolean isPressed;
    private int directionX;
    private int directionY;
    private Player player;

    private Bitmap upArrowBitMap;
    private Bitmap downArrowBitMap;
    private Bitmap rightArrowBitMap;
    private Bitmap leftArrowBitMap;




    public ControlPad(Context context, int tileSize, int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.halfTileSize = this.tileSize / 2;
        this.player = player;
        upArrowBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.up), tileSize, tileSize, false);
        leftArrowBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.left), tileSize, tileSize, false);
        downArrowBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.down), tileSize, tileSize, false);
        rightArrowBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.right), tileSize, tileSize, false);

    }


    public void draw(Canvas canvas) {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        canvas.drawBitmap(upArrowBitMap, x, y - 120, blackPaint);

        canvas.drawBitmap(downArrowBitMap, x, y + 120, blackPaint);
        canvas.drawBitmap(leftArrowBitMap, x - 120, y, blackPaint);
        canvas.drawBitmap(rightArrowBitMap, x + 120, y, blackPaint);

    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        if ((x - 180) < touchPositionX
                && touchPositionX < (x + 180)
                && (y - 180) < touchPositionY
                && touchPositionY < (y + 180)) {
            return true;
        }
        return false;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }
    public boolean getIsPressed() {
        return isPressed;
    }
    public void setDirection(double inputX, double inputY) {
        if ((inputX > (x)) && inputX < (x + tileSize)) {
            if ((inputY > (y  + tileSize))  && ((inputY < (y + 2 * tileSize)))) {
                player.moveDown();
            } else if ((inputY < (y))
                    && ((inputY > (y - tileSize)))) {
                player.moveUp();
            } else {
                directionX = 0; //center
                directionY = 0;
            }
        } else if ((inputY > (y)) && inputY < (y + tileSize)) {
            if ((inputX > (x  + tileSize))  && ((inputX < (x + 2 * tileSize)))) {
                player.moveRight();
            } else if ((inputX < (x))
                    && ((inputX > (x - tileSize)))) {
                player.moveLeft();
            } else {
                directionX = 0;
                directionY = 0;
            }
        }
    }
    public int getDirectionX() {
        return directionX;
    }
    public int getDirectionY() {
        return directionY;
    }

}
