package com.example.dinodash.game;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;

    public GameView(Context context){
        super(context);
        initView();
    }

    public void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        System.out.println("Motion event action: "+action);
        if (action == MotionEvent.ACTION_DOWN){
            if(GameConstants.getGameEngine().gameState == 0){
                GameConstants.getGameEngine().gameState = 1;
            }
            if(GameConstants.playerGrounded == true){
                GameConstants.getGameEngine().player.setVelocity(GameConstants.JUMP_VELOCITY);
                GameConstants.playerGrounded = false;
            }
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if(!gameThread.isRunning()){
            // maybe gameThread is not initialized. Initialize and start it
            gameThread = new GameThread(holder);
            gameThread.start();
        }
        else{
            // start game thread
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setRunning(false);
            boolean retry = true;
            while (retry){
                try{
                    gameThread.join();
                    retry = false;
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
