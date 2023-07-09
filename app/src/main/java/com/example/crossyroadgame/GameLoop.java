package com.example.crossyroadgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private GameMap gameMap;
    private boolean running;
    private SurfaceHolder surfaceHolder;
    public GameLoop(GameMap gameMap, SurfaceHolder surfaceHolder) {
        this.gameMap = gameMap;
        this.surfaceHolder = surfaceHolder;
        running = false;
    }

    public void startLoop() {
        running = true;
        super.start();
    }

    @Override
    public void run() {
        super.run();
        long startTime = System.nanoTime();

        while (running)  {
            Canvas canvas = null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas)  {
                    this.gameMap.update();
                    this.gameMap.draw(canvas);
                }
            } catch (Exception e)  {
                // Do nothing.
            } finally {
                if (canvas != null)  {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime();
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = (now - startTime) / 1000000;
            if (waitTime < 10)  {
                waitTime = 10; // Millisecond.
            }
            System.out.print(" Wait Time=" + waitTime);

            try {
                // Sleep.
                this.sleep(waitTime);
            } catch (InterruptedException e)  {

            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }
}
