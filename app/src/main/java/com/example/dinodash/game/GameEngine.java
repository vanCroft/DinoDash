package com.example.dinodash.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dinodash.entity.BackgroundImage;
import com.example.dinodash.entity.Obstacles;
import com.example.dinodash.entity.Player;
import com.example.dinodash.entity.PlayerDead;
import com.example.dinodash.ui.screens.GameOver;
import com.example.dinodash.utils.Helper;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    BackgroundImage backgroundImage;
    Player player;
    PlayerDead playerDead;
    Random random;
    int score;
    Paint scorePaint;
    ArrayList<Obstacles> obstaclesList;
    Obstacles obstacle;
    boolean obstacleSpawed;
    int playerFrame, playerJumpFrame;
    int points;
    int gameState;
    final int TEXT_SIZE = 80;
    Bitmap obs;
    boolean collision = false;

    public GameEngine(){
        backgroundImage = new BackgroundImage();
        player = new Player();
        playerDead = new PlayerDead();
        obstacleSpawed = false;
        gameState = 1;
        random = new Random();
        score = 0;

        playerFrame = 0;
        playerJumpFrame = 0;

        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        obstaclesList = new ArrayList<Obstacles>();
        obstaclesList.add(new Obstacles("egg"));
        obstaclesList.add(new Obstacles("egg"));
        obstaclesList.add(new Obstacles("rottenEgg"));
        points = 0;
    }

    public void updateAndDrawBackroundImage(Canvas canvas){
        // Draw the backgorund image on canvas
        if(collision == false){
            backgroundImage.setBackgroundImageX(backgroundImage.getBackgroundImageX() - backgroundImage.getBackgroundImageVelocity());
            if(backgroundImage.getBackgroundImageX() < - GameConstants.bitmapBank.getBackgroundWidth()){
                backgroundImage.setBackgroundImageX(0);
            }
        }
        canvas.drawBitmap(GameConstants.bitmapBank.getBackground(), backgroundImage.getBackgroundImageX(), backgroundImage.getBackgroundImageY(),null);

        if(backgroundImage.getBackgroundImageX() < -(GameConstants.bitmapBank.getBackgroundWidth() - Helper.SCREEN_WIDTH)){
            canvas.drawBitmap(GameConstants.bitmapBank.getBackground(), backgroundImage.getBackgroundImageX() + GameConstants.bitmapBank.getBackgroundWidth(),backgroundImage.getBackgroundImageY(),null);
        }
    }

    public void updateAndDrawPlayer(Canvas canvas){
        if(gameState == 1){
            if(GameConstants.playerGrounded == true){
                //System.out.println("draw player grounded");
                player.setVelocity(-GameConstants.JUMP_VELOCITY);
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayer(playerFrame), player.getpX(), player.getpY(), null);
                playerFrame++;
                if(playerFrame > GameConstants.getBitmapBank().getPlayer().length -1){
                    playerFrame = 0;
                }
                // check collision
            }
            else{
                player.setVelocity(player.getVelocity() + GameConstants.gravity);
                player.setpY(player.getpY() - 100);
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
            if(obs != null){
                canvas.drawBitmap(obs, obstacle.cX,obstacle.cY,null);
                // Handle collision
                int spaceBetweenPlayerAndObstacle = obstacle.cX - player.pX - GameConstants.getBitmapBank().getPlayerWidth();
                /*if(GameConstants.playerGrounded && spaceBetweenPlayerAndObstacle <= 20 && spaceBetweenPlayerAndObstacle > -20){
                    // reset obstacle and add points
                    points += getPoint(obstacle.type);
                    if(points <=0){
                        points = 0;
                        gameState = 2;
                    }
                }*/
                if(spaceBetweenPlayerAndObstacle <= 20){
                    if(GameConstants.playerGrounded){
                        obstacle.reset();
                        points += getPoint(obstacle.type);
                        if(points <= 0){
                            System.out.println("Game over");
                            // TODO: save score and display game over screen
                            gameState = 2;
                            Context context = GameConstants.gameActivityContext;
                            Intent intent = new Intent(context, GameOver.class);
                            intent.putExtra("points", points);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                        else{
                            obstacleSpawed = false;
                        }

                    }
                }

            }
        }
    }

    public void setGameState(int state){
        gameState = state;
    }

    private int getPoint(String obstacleType){
        if(obstacleType != null && GameConstants.playerGrounded){
            switch (obstacleType.toLowerCase()){
                case "rottenegg":
                    return -10;
                case "egg":
                    return 15;
                default:
                    return 0;
            }
        }
        return 0;

    }
}
