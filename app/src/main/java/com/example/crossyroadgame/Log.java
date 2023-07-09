package com.example.crossyroadgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Log extends MovingObject {

    private final int cols;
    private final int rows;
    private int tileSize;

    private int bitmapID;

    public Log(Context context, int[] tileSizeRowsCols, int length, int bitmapId,
                   String direction, int speed, int rowsFromBottom) {
        super(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(
                                context.getResources(), bitmapId),
                        length * tileSizeRowsCols[0], tileSizeRowsCols[0], false), speed,
                direction, rowsFromBottom, length, tileSizeRowsCols);
        this.tileSize = tileSizeRowsCols[0];
        this.rows = tileSizeRowsCols[1];
        this.cols = tileSizeRowsCols[2];
        this.bitmapID = bitmapId;
    }

    public Log(int[] tileSizeRowsCols, int length, int bitmapId,
               String direction, int speed, int rowsFromBottom) {
        super(null, speed,
                direction, rowsFromBottom, length, tileSizeRowsCols);
        this.tileSize = tileSizeRowsCols[0];
        this.rows = tileSizeRowsCols[1];
        this.cols = tileSizeRowsCols[2];
        this.bitmapID = bitmapId;
    }


    public int getBitMap() {
        return bitmapID;
    }

}
