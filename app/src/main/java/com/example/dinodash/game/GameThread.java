package com.example.dinodash.game;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33;

    public GameThread(SurfaceHolder holder){
        surfaceHolder = holder;
        isRunning = true;
    }

    public void run(){
        while(isRunning){
            startTime = SystemClock.uptimeMillis();
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null){
                synchronized (surfaceHolder){
                    GameConstants.getGameEngine().updateAndDrawBackroundImage(canvas);
                    GameConstants.getGameEngine().updateAndDrawRemainingTime(canvas);
                    GameConstants.getGameEngine().updateAndDrawPlayer(canvas);
                    GameConstants.getGameEngine().updateAndDrawObstacles(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            loopTime = SystemClock.uptimeMillis() - startTime;
            if(loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
