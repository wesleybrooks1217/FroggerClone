package com.example.crossyroadgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class MovingObject {


    protected int length;
    private int speed;
    private final Bitmap bitmap;
    private final String direction;
    private int rowsFromBottom;
    private int xPixels;

    public MovingObject(Bitmap bitmap, int speed, String direction, int rowsFromBottom, int length,
                        int[] tileSizeRowsCols) {
        this.bitmap = bitmap;
        this.speed = speed;
        this.direction = direction;
        this.rowsFromBottom = rowsFromBottom;
        this.cols = tileSizeRowsCols[2];
        this.tileSize = tileSizeRowsCols[0];
        this.rows = tileSizeRowsCols[1];
        this.length = length;
        resetXPixels();

    }

    public void draw(Canvas canvas) {
        if (direction.equals("left")) {
            if (getxPixels() > -3 * tileSize) {
                canvas.drawBitmap(getBitmap(), getxPixels() + tileSize,
                        (rows - 1 - rowsFromBottom) * tileSize, null);
                setxPixels(getxPixels() -  8 * getSpeed());
            } else {
                resetXPixels();
            }
        } else {
            if (getxPixels() < cols * tileSize) {
                canvas.drawBitmap(getBitmap(), getxPixels() + tileSize,
                        (rows - 1 - rowsFromBottom) * tileSize, null);
                setxPixels(getxPixels() +  8 * getSpeed());
            } else {
                resetXPixels();
            }
        }
    }

    private int rows;
    private int tileSize;
    private int cols;

    public int getSpeed() {
        return speed;
    }

    public void setxPixels(int xPixels) {
        this.xPixels = xPixels;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getDirection() {
        return direction;
    }

    public int getRowsFromBottom() {
        return this.rowsFromBottom;
    }

    public int getxPixels() {
        return xPixels;
    }
    public void resetXPixels() {
        if (direction.equals("left")) {
            setXPixels((cols - 1) * tileSize);
        } else {
            setXPixels(-3 * tileSize);
        }
    }
    public int getLength() {
        return length;
    }
    public int getYPixels() {
        return (rows - 1 - rowsFromBottom) * tileSize;
    }


    public void setXPixels(int xPixels) {
        this.xPixels = xPixels;
    }
}
