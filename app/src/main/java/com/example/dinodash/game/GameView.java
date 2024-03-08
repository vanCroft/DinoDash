package com.example.dinodash.game;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.dinodash.R;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;
    MediaPlayer musicPlayer;
    MediaPlayer jumpPlayer;

    public GameView(Context context){
        super(context);
        musicPlayer = MediaPlayer.create(context, R.raw.epic);
        jumpPlayer = MediaPlayer.create(context, R.raw.jump);
        musicPlayer.setLooping(true);
        initView();
    }

    public void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
        musicPlayer.start();
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
                jumpPlayer.start();
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

    public void pause(){
        musicPlayer.pause();
        GameConstants.getGameEngine().setGameState(0);
    }

    public void resume(){
        GameConstants.getGameEngine().setGameState(1);
        musicPlayer.start();
    }

    public void stop(){
        musicPlayer.stop();
        jumpPlayer.stop();
    }
}
