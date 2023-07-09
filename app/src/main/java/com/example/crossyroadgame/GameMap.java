package com.example.crossyroadgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class GameMap extends SurfaceView implements SurfaceHolder.Callback {

    private final Player player;
    private final ControlPad controlPad;
    private Vehicle vehicle;
    private int tileSize = 120;
    private Canvas canvas;
    private Bitmap playerLeftBitMap;

    private Bitmap playerRightBitMap;

    private Bitmap goalTileBitMap;
    private Bitmap riverTileBitMap;
    private Bitmap startTileBitMap;
    private Bitmap roadTileBitMap;
    private Bitmap safeTileBitMap;

    private Bitmap upArrowBitMap;
    private Bitmap downArrowBitMap;
    private Bitmap leftArrowBitMap;
    private Bitmap rightArrowBitMap;
    private Vehicle vehicle2;

    private Vehicle vehicle3;

    private Log log1;

    private Log log2;

    private Log log3;

    private Log log4;

    private Log log5;

    private DisplayMetrics displayMetrics;
    private int width;
    private int height;
    private GameLoop gameLoop;
    private Bitmap[][] tileLocations;
    private int cols;
    private int rows;

    private int controlPadX;
    private int controlPadY;
    private Display display;
    private Context context;

    //private LeafObject leaf1;

    //private LeafObject leaf2;

    //private LeafObject leaf3;

    //private LeafObject leaf4;

    private LeafObject[] leafObjects;


    private TileManagement tileManager;
    public GameMap(Context context, String playerName, int playerDifficulty,
                   int[] playerSpriteIdArray) {
        super(context);
        this.context = context;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        displayMetrics = new DisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        rows = 11;
        cols = 9;
        int[] tileSizeRowsCols = new int[] {tileSize, rows, cols};
        canvas = new Canvas();
        tileManager = new TileManagement(context, rows, cols, tileSize);

        player = new Player(playerName, tileSize, rows, cols,
                6 - playerDifficulty, playerSpriteIdArray);

        playerLeftBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), playerSpriteIdArray[0]), tileSize, tileSize, false);
        playerRightBitMap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(), playerSpriteIdArray[1]), tileSize, tileSize, false);


        controlPadX = 500;
        controlPadY = 500;
        controlPad = new ControlPad(context, tileSize, controlPadX, controlPadY, player);
        vehicle = new Vehicle(context, tileSizeRowsCols, 2,
                R.drawable.vehicle1, "left", 3, 1);
        vehicle2 = new Vehicle(context, tileSizeRowsCols, 3,
                R.drawable.vehicle2, "right", 2, 2);
        vehicle3 = new Vehicle(context, tileSizeRowsCols, 5,
                R.drawable.vehicle3, "left", 1, 3);



        log1 = new Log(context, tileSizeRowsCols, 4,
                R.drawable.longlog, "left", 2, 5);
        log2 = new Log(context, tileSizeRowsCols, 3,
                R.drawable.shortlog, "right", 1, 6);
        log3 = new Log(context, tileSizeRowsCols, 3,
                R.drawable.shortlog, "left", 2, 7);
        log4 = new Log(context, tileSizeRowsCols, 4,
                R.drawable.longlog, "right", 1, 8);
        log5 = new Log(context, tileSizeRowsCols, 5,
                R.drawable.longlog, "left", 2, 9);

        //leaf1 = new LeafObject(context, 5, rows, cols, tileSize, R.drawable.leaf, 1, 25);
        //leaf2 = new LeafObject(context, 5, rows, cols, tileSize, R.drawable.leaf, 1, 750);
        //        leafObjects = new LeafObject[] {
        //            new LeafObject(context, 5, tileSizeRowsCols, R.drawable.leaf, 1, 0),
        //            new LeafObject(context, 6, tileSizeRowsCols, R.drawable.leaf, 1, 0),
        //            new LeafObject(context, 7, tileSizeRowsCols, R.drawable.leaf, 1, 0),
        //            new LeafObject(context, 8, tileSizeRowsCols, R.drawable.leaf, 1, 0),
        //            new LeafObject(context, 9, tileSizeRowsCols, R.drawable.leaf, 1, 0),
        //        };


        this.gameLoop = new GameLoop(this, surfaceHolder);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tileManager.draw(canvas);
        //        for (LeafObject leafObject : leafObjects) {
        //            leafObject.draw(canvas);
        //        }
        log1.draw(canvas);
        log2.draw(canvas);
        log3.draw(canvas);
        log4.draw(canvas);
        log5.draw(canvas);

        player.draw(canvas, playerLeftBitMap, playerRightBitMap);
        vehicle.draw(canvas);
        vehicle2.draw(canvas);
        vehicle3.draw(canvas);
        controlPad.draw(canvas);
        //leaf1.draw(canvas);
        //leaf2.draw(canvas);


        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(42);
        String text = String.format("Name: %s, Lives: %d, Difficulty: %s, Score: %d",
                player.getName(), player.getLives(), player.getDifficulty(), player.getScore());
        canvas.drawText(text, 20, 1400, textPaint);
        if (intersect(player, vehicle)
                || intersect(player, vehicle2)
                || intersect(player, vehicle3)) {
            player.loseLife();
        }
        if (intersect(player, log1) || intersect(player, log3) || intersect(player, log5)) {
            player.rideLogLeft(log1);
            if ((player.getX() == 0)) {
                player.loseLife();
            }
        } else if (intersect(player, log2) || intersect(player, log4)) {
            player.rideLogRight(log2);
            if ((player.getX() == cols - 1)) {
                player.loseLife();
            }
        } else if (player.getY() != 0 && player.getY() <= 5 && player.getY() < 10) {
            player.loseLife();
        }
        //if (player.getY() <= 5 && player.getY() != 0 && !safeOnRiver(player, leafObjects)) {
        //            player.loseLife();
        //        }
        if (player.getLives() <= 0) {
            Intent intent = new Intent().setClass(getContext(), GameOver.class);
            intent.putExtra("score", player.getScore());

            intent.putExtra("win", false);
            // ((Activity) getContext()).finish();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ((Activity) getContext()).startActivity(intent);
            //Intent intent = new Intent(GameScreen.this, GameOver.class);
        }

        if (player.getY() == 0) {
            Intent intent = new Intent().setClass(getContext(), GameOver.class);
            intent.putExtra("score", player.getScore());
            intent.putExtra("win", true);
            // ((Activity) getContext()).finish();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ((Activity) getContext()).startActivity(intent);
        }
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void update() {

        player.update(controlPad);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controlPad.setDirection((double) event.getX(), (double) event.getY());


        return super.onTouchEvent(event);
    }

    private boolean intersect(Player player, MovingObject movingObject) {
        Rect playerRect = new Rect(player.getX() * tileSize, player.getY() * tileSize,
                player.getX() * tileSize + tileSize,
                player.getY() * tileSize + tileSize);
        Rect movingObjectRect = new Rect(movingObject.getxPixels() + tileSize,
                movingObject.getYPixels(),
                movingObject.getxPixels() + movingObject.getLength() * tileSize  + tileSize,
                movingObject.getYPixels() + tileSize);
        return playerRect.intersect(movingObjectRect);
    }


    //    public static boolean safeOnRiver(Player player, LeafObject[] leafObjects) {
    //        if (player == null) {
    //            return false;
    //        }
    //        if (leafObjects == null) {
    //            return false;
    //        }
    //        Rect playerRect = new Rect(player.getX() * player.getTileSize(),
    //                player.getY() * player.getTileSize(),
    //                player.getX() * player.getTileSize() + player.getTileSize(),
    //                player.getY() * player.getTileSize() + player.getTileSize());
    //        for (LeafObject leaf: leafObjects) {
    //            Rect leafObjectRect = new Rect(leaf.getXPixels() + player.getTileSize(),
    //                    leaf.getYPixels(),
    //                    leaf.getXPixels() + leaf.getLength()
    //                            * player.getTileSize() + player.getTileSize(),
    //                    leaf.getYPixels() + player.getTileSize());
    //            if (playerRect.intersect(leafObjectRect)) {
    //                return true;
    //            }
    //        }
    //        return false;

}
