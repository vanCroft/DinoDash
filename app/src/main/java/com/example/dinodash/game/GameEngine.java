package com.example.dinodash.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.SystemClock;

import com.example.dinodash.entity.BackgroundImage;
import com.example.dinodash.entity.Obstacles;
import com.example.dinodash.entity.Player;
import com.example.dinodash.ui.screens.GameOver;
import com.example.dinodash.utils.Helper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    BackgroundImage backgroundImage;
    Player player;
    Random random;
    int score;
    Paint scorePaint;
    Paint timePaint;
    ArrayList<Obstacles> obstaclesList;
    Obstacles obstacle;
    boolean obstacleSpawed;
    int playerFrame, playerJumpFrame;
    int points;
    int gameState;
    final int TEXT_SIZE = 80;
    Bitmap obs;
    boolean collision = false;
    long startTime;
    long remainingTime = GameConstants.PLAY_TIME;
    long prevDiff = 0;

    public GameEngine(){
        backgroundImage = new BackgroundImage();
        player = new Player();
        obstacleSpawed = false;
        gameState = 1;
        random = new Random();
        score = 0;

        playerFrame = 0;
        playerJumpFrame = 0;

        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);;
        scorePaint.setTextAlign(Paint.Align.LEFT);

        timePaint = new Paint();
        timePaint.setTypeface(Typeface.DEFAULT_BOLD);
        timePaint.setColor(Color.GREEN);
        timePaint.setTextSize(TEXT_SIZE);
        timePaint.setTextAlign(Paint.Align.LEFT);

        obstaclesList = new ArrayList<Obstacles>();
        obstaclesList.add(new Obstacles("egg"));
        obstaclesList.add(new Obstacles("tree"));
        obstaclesList.add(new Obstacles("egg"));
        obstaclesList.add(new Obstacles("rottenEgg"));
        obstaclesList.add(new Obstacles("egg"));

        points = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startTime = Instant.now().getEpochSecond();
        }
        else {
            startTime = SystemClock.uptimeMillis() / 1000;
        }
    }

    public void updateAndDrawBackroundImage(Canvas canvas){
        // Draw the backgorund image on canvas
        backgroundImage.setBackgroundImageX(backgroundImage.getBackgroundImageX() - backgroundImage.getBackgroundImageVelocity());
        if(backgroundImage.getBackgroundImageX() < - GameConstants.bitmapBank.getBackgroundWidth()){
            backgroundImage.setBackgroundImageX(0);
        }
        canvas.drawBitmap(GameConstants.bitmapBank.getBackground(), backgroundImage.getBackgroundImageX(), backgroundImage.getBackgroundImageY(),null);
        // loop backgorund image
        if(backgroundImage.getBackgroundImageX() < -(GameConstants.bitmapBank.getBackgroundWidth() - Helper.SCREEN_WIDTH)){
            canvas.drawBitmap(GameConstants.bitmapBank.getBackground(), backgroundImage.getBackgroundImageX() + GameConstants.bitmapBank.getBackgroundWidth(),backgroundImage.getBackgroundImageY(),null);
        }
    }

    public void updateAndDrawPlayer(Canvas canvas){
        if(gameState == 1){
            if(GameConstants.playerGrounded == true){
                player.setVelocity(-GameConstants.JUMP_VELOCITY);
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayer(playerFrame), player.getpX(), player.getpY(), null);
                playerFrame++;
                if(playerFrame > GameConstants.getBitmapBank().getPlayer().length -1){
                    playerFrame = 0;
                }
            }
            else{
                // player jump height
                int pjY = Helper.SCREEN_HEIGHT - GameConstants.getBitmapBank().getPlayerJumpHeight() - GameConstants.getBitmapBank().getEggHeight()-100;
                if(pjY < 100){
                    pjY = 100;
                }

                player.setpY(pjY);
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayerJump(playerJumpFrame),player.getpX(),player.getpY(),null);
                playerJumpFrame++;
                if(playerJumpFrame > GameConstants.getBitmapBank().getPlayerJump().length -1){
                    playerJumpFrame = 0;
                    player.setpY(player.pYInitial);
                    GameConstants.playerGrounded = true;
                }
            }
            canvas.drawText("Punkte: "+(points < 0 ? 0: points), 0, TEXT_SIZE, scorePaint);
        }

    }

    public void updateAndDrawObstacles(Canvas canvas){
        if(gameState == 1){
            if(obstacleSpawed == false){
                int randIndex = random.nextInt(obstaclesList.size());
                obstacle = obstaclesList.get(randIndex);
                obstacleSpawed = true;
            }
            obstacle.cX -= obstacle.velocity;
            if(obstacle.type.equalsIgnoreCase("egg")){
                obs = GameConstants.getBitmapBank().getEgg();
            }
            else if(obstacle.type.equalsIgnoreCase("rottenEgg")){
                obs = GameConstants.getBitmapBank().getRottenEgg();
            }
            else if(obstacle.type.equalsIgnoreCase("tree")){
                obs = GameConstants.getBitmapBank().getTree();
            }
            if(obs != null){
                canvas.drawBitmap(obs, obstacle.cX,obstacle.cY,null);
                // Handle collision
                int spaceBetweenPlayerAndObstacle = obstacle.cX - player.pX - GameConstants.getBitmapBank().getPlayerWidth();
                if (spaceBetweenPlayerAndObstacle <= 20 && spaceBetweenPlayerAndObstacle >=-20){
                    if(GameConstants.playerGrounded){
                        obstacle.reset();
                        points += getPoint(obstacle.type);
                        obstacleSpawed = false;
                        if(points <=0){
                            points = 0;
                            gameState = 2;
                            displayGameOver();
                        }
                    }
                }
                else if(obstacle.cX < -player.pX){
                    obstacle.reset();
                    obstacleSpawed = false;
                }
            }
        }
    }

    public void updateAndDrawRemainingTime(Canvas canvas){
        setRemainingTime();
        if(remainingTime < 11){
            timePaint.setColor(Color.RED);
        }
        else {
            timePaint.setColor(Color.GREEN);
        }
        canvas.drawText("Zeit: "+remainingTime,Helper.SCREEN_WIDTH - 350,TEXT_SIZE,timePaint);
    }

    public void setGameState(int state){
        gameState = state;
    }

    private void setRemainingTime(){
        if(gameState == 1){
            long diff = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                diff = Instant.now().getEpochSecond() - startTime;
            }
            else {
                diff = (SystemClock.uptimeMillis() / 1000) - startTime;
            }
            if(diff > prevDiff){
                this.remainingTime--;
                prevDiff = diff;
                if(remainingTime <=0){
                    displayGameOver();
                }
            }
        }
        else{
            // reset anything
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startTime = Instant.now().getEpochSecond();
            }
            else {
                startTime = (SystemClock.uptimeMillis() / 1000);
            }
            prevDiff = 0;
        }
    }

    private int getPoint(String obstacleType){
        if(obstacleType != null && GameConstants.playerGrounded){
            switch (obstacleType.toLowerCase()){
                case "rottenegg":
                    return -10;
                case "egg":
                    return 15;
                case "tree":
                    return -15;
                default:
                    return 0;
            }
        }
        return 0;
    }

    private void displayGameOver(){
        Context context = GameConstants.gameActivityContext;
        SharedPreferences pref = context.getSharedPreferences("AppBestScore", Context.MODE_PRIVATE);
        int bestScore = pref.getInt("bestScore",0);
        if(points > 0){
            // Open file and save score
            Helper.writeHighScore(context,points);
            // save best score
            if(points > bestScore){
                bestScore = points;
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("bestScore",bestScore);
                editor.commit();
            }
        }
        // go to game over screen
        Intent intent = new Intent(context, GameOver.class);
        intent.putExtra("points", points < 0 ? 0: points);
        intent.putExtra("bestScore",bestScore);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
