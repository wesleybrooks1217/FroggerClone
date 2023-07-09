package com.example.crossyroadgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class TileManagement {
    private Bitmap goalTileBitMap;
    private Bitmap riverTileBitMap;
    private Bitmap startTileBitMap;
    private Bitmap roadTileBitMap;
    private Bitmap safeTileBitMap;
    private Bitmap[][] tileLocations;
    private Canvas canvas;
    private Bitmap[] roadLine;
    private Bitmap[] riverLine;
    private Bitmap[] goalLine;
    private Bitmap[] safeLine;
    private Bitmap[] startLine;
    private int rows;
    private int cols;
    private int tileSize;
    private Context context;
    public TileManagement(Context context, int rows, int cols, int tileSize) {
        this.context = context;
        this.tileSize = tileSize;
        this.rows = rows;
        this.cols = cols;
        startTileBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.starting_tile),
                tileSize, tileSize, false);
        goalTileBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.goal_tile),
                tileSize, tileSize, false);
        riverTileBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.river_tile2),
                tileSize, tileSize, false);
        roadTileBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.sand_tile2),
                tileSize, tileSize, false);
        safeTileBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.safe_tile),
                tileSize, tileSize, false);
        tileLocations = new Bitmap[rows][cols];
        roadLine = new Bitmap[cols];
        riverLine = new Bitmap[cols];
        goalLine = new Bitmap[cols];
        safeLine = new Bitmap[cols];
        startLine = new Bitmap[cols];
        fillBitMap();
    }

    public void fillBitMap() {
        for (int i = 0; i < cols; i++) {
            roadLine[i] = roadTileBitMap;
            riverLine[i] = riverTileBitMap;
            goalLine[i] = goalTileBitMap;
            safeLine[i] = safeTileBitMap;
            startLine[i] = startTileBitMap;
        }

        for (int i = 0; i < rows; i++) {
            if (i == 0) {
                tileLocations[i] = goalLine;
            } else if (i < 6) {
                tileLocations[i] = riverLine;
            } else if (i == 6) {
                tileLocations[i] = safeLine;
            } else if (i < 10) {
                tileLocations[i] = roadLine;
            } else {
                tileLocations[i] = startLine;
            }
        }
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //System.out.println(tileLocations[i][j]);
                if (tileLocations != null) {
                    canvas.drawBitmap(tileLocations[i][j], j * tileSize + 0 / 2,
                            i * tileSize + 0 / 2, null);
                }
            }

        }
    }
}
