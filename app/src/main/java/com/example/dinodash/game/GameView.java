package com.example.dinodash.game;

import android.content.Context;
import android.content.SharedPreferences;
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
    boolean playMusic;
    boolean playSound;

    public GameView(Context context){
        super(context);
        SharedPreferences pref = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        playMusic = pref.getBoolean("EpicMusic", true);
        playSound = pref.getBoolean("JurassicSound", true);
        if(playMusic){
            musicPlayer = MediaPlayer.create(context, R.raw.epic);
            musicPlayer.setLooping(true);
        }
        if(playSound){
            jumpPlayer = MediaPlayer.create(context, R.raw.jump);
        }
        initView();
    }

    public void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
        if(musicPlayer != null){
            musicPlayer.start();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            if(GameConstants.getGameEngine().gameState == 0){
                GameConstants.getGameEngine().gameState = 1;
            }
            if(GameConstants.playerGrounded == true){
                GameConstants.getGameEngine().player.setVelocity(GameConstants.JUMP_VELOCITY);
                if(jumpPlayer != null){
                    jumpPlayer.start();
                }
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

    public void pause(){
        if(musicPlayer != null){
            musicPlayer.pause();
        }
        GameConstants.getGameEngine().setGameState(0);
    }

    public void resume(){
        GameConstants.getGameEngine().setGameState(1);
        if(musicPlayer != null){
            musicPlayer.start();
        }
    }

    public void stop(){
        if (musicPlayer != null){
            musicPlayer.stop();
            musicPlayer.reset();
            musicPlayer.release();
        }
        if(jumpPlayer != null){
            jumpPlayer.stop();
            jumpPlayer.reset();
            jumpPlayer.release();
        }
    }
}
