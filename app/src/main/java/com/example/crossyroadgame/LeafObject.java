package com.example.crossyroadgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class LeafObject {

    private final Bitmap bitmap;

    private int rowsFromBottom;

    private final int rows;

    private final int cols;

    private int tileSize;

    private final int xPixels;

    private int length;

    public LeafObject(Context context, int rowsFromBottom, int[] tileSizeRowsCols,
                      int bitmapID, int length, int xPixels) {
        this.bitmap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(), bitmapID),
                length * tileSizeRowsCols[0], tileSizeRowsCols[0], false);
        this.rowsFromBottom = rowsFromBottom;
        this.tileSize = tileSizeRowsCols[0];
        this.rows = tileSizeRowsCols[1];
        this.cols = tileSizeRowsCols[2];
        this.xPixels = xPixels;
        this.length = length;
    }

    public LeafObject(int rowsFromBottom, int rows, int cols, int tileSize,
                      int bitmapID, int length, int xPixels) {
        this.bitmap = null;
        this.rowsFromBottom = rowsFromBottom;
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;
        this.xPixels = xPixels;
        this.length = length;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(getBitmap(), getXPixels() + tileSize,
                (rows - 1 - rowsFromBottom) * tileSize, null);
    }

    private Bitmap getBitmap() {
        return this.bitmap;
    }

    public int getXPixels() {
        return this.xPixels;
    }

    public int getYPixels() {
        return (rows - 1 - rowsFromBottom) * tileSize;
    }

    public int getLength() {
        return this.length;
    }

    public int getRowsFromBottom() {
        return this.rowsFromBottom;
    }
    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }
    public int getTileSize() {
        return this.tileSize;
    }




}
