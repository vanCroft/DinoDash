package com.example.dinodash.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dinodash.entity.BackgroundImage;
import com.example.dinodash.entity.BigEgg;
import com.example.dinodash.entity.MediumEgg;
import com.example.dinodash.entity.Obstacles;
import com.example.dinodash.entity.Player;
import com.example.dinodash.entity.PlayerDead;
import com.example.dinodash.entity.RottenEgg;
import com.example.dinodash.entity.SmallEgg;
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
    int playerFrame, playerJumpFrame, playerDeadFrame;
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
        playerDeadFrame = 0;
        playerJumpFrame = 0;

        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        obstaclesList = new ArrayList<Obstacles>();
        obstaclesList.add(new Obstacles("smallEgg"));
        obstaclesList.add(new Obstacles("mediumEgg"));
        obstaclesList.add(new Obstacles("bigEgg"));
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
        canvas.drawBitmap(GameConstants.bitmapBank.getBackground(), backgroundImage.getBackgroundImageX(), backgroundImage.getGetBackgroundImageY(),null);
        if(backgroundImage.getBackgroundImageX() < -(GameConstants.bitmapBank.getBackgroundWidth() - Helper.SCREEN_WIDTH)){
            canvas.drawBitmap(GameConstants.bitmapBank.getBackground(), backgroundImage.getBackgroundImageX() + GameConstants.bitmapBank.getBackgroundWidth(),backgroundImage.getGetBackgroundImageY(),null);
        }
    }

    public void updateAndDrawPlayer(Canvas canvas){
        if(gameState == 1){
            if(GameConstants.playerGrounded == true){
                //System.out.println("draw player grounded");
                player.setVelocity(player.getVelocity() + GameConstants.gravity);
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayer(playerFrame), player.getpX(), player.getpY(), null);
                playerFrame++;
                if(playerFrame >= GameConstants.getBitmapBank().getPlayer().length -1){
                    playerFrame = 0;
                }
                // check collision
            }
            else{
                //System.out.println("draw player jumping");
                //player.setVelocity(player.getVelocity() + GameConstants.gravity);
                player.setpY(player.getpY() - 200);
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayerJump(playerJumpFrame),player.getpX(),player.getpY(),null);
                playerJumpFrame++;
                if(playerJumpFrame >  GameConstants.getBitmapBank().getPlayerJump().length -1){
                    playerJumpFrame = 0;
                    player.setpY(player.pYInitial);
                    GameConstants.playerGrounded = true;
                }
            }
            canvas.drawText("Punkte: "+points, 0, TEXT_SIZE, scorePaint);
        }

    }

    /* public void updateAndDrawPlayers(Canvas canvas)  {
        if(gameState == 1){
            if(collision == false && GameConstants.playerGrounded == false){
                player.setVelocity(player.getVelocity() + GameConstants.gravity);
                player.setpY(player.getpY() + player.getVelocity());
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayerJump(playerJumpFrame),player.getpX(),player.getpY(),null);
                playerJumpFrame++;
                if(playerJumpFrame > 3){
                    playerJumpFrame = 0;
                }
                if(player.getpY() >= player.pYInitial){
                    player.setpY(player.pYInitial);
                    GameConstants.playerGrounded = true;
                }
            }
            else if (collision == true && GameConstants.playerGrounded == false){
                playerDead.setVelocity(playerDead.getVelocity() + GameConstants.gravity);
                playerDead.setpY(playerDead.getpY() + playerDead.getVelocity());
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayerDead(playerDeadFrame),playerDead.getpX(), playerDead.getpY(),null);
                playerDeadFrame++;
                if(playerDeadFrame == 1){
                    gameState = 2; // Game over state
                    // TODO: save current score and display game over activiy
                }
                if(playerDead.getpY() >= playerDead.pYInitial){
                    playerDead.setpY(playerDead.pYInitial);
                    GameConstants.playerGrounded = true;
                }
            }
            else if(collision == true && GameConstants.playerGrounded){
                System.out.println("Draw dead player");
                canvas.drawBitmap(GameConstants.getBitmapBank().getPlayerDead(playerDeadFrame),playerDead.getpX(), playerDead.getpY(), null);
                if(playerDead.getpY() >= playerDead.pYInitial){
                    playerDead.setpY(playerDead.pYInitial);
                    GameConstants.playerGrounded = true;
                }
            }
            if(obstacle != null){
                if(obstacle.cX <= player.pX
                        && obstacle.cX + obstacle.width >= player.pX
                        && obstacle.cY > player.pY
                        && obstacle.cY <= player.pY + GameConstants.getBitmapBank().getPlayerHeight()){
                    collision = true;
                    obstacle.reset();
                }
                canvas.drawText("Punkte: "+points, 0, TEXT_SIZE, scorePaint);
            }
        }
    }*/

    public void updateAndDrawObstacles(Canvas canvas){
        if(gameState == 1){
            if(obstacleSpawed == false){
                int randIndex = random.nextInt(obstaclesList.size());
                obstacle = obstaclesList.get(randIndex);
                obstacleSpawed = true;
            }
            if(collision == false){
                obstacle.cX -= obstacle.velocity;
                if(obstacle.type.equalsIgnoreCase("smallEgg")){
                    //System.out.println("get small egg bitmap");
                    obs = GameConstants.getBitmapBank().getSmallEgg();
                }
                else if(obstacle.type.equalsIgnoreCase("mediumEgg")){
                    //System.out.println("get medium egg bitmap");
                    obs = GameConstants.getBitmapBank().getMediumEgg();
                }
                else if(obstacle.type.equalsIgnoreCase("bigEgg")){
                    //System.out.println("get big egg bitmap");
                    obs = GameConstants.getBitmapBank().getBigEgg();
                }
                else if(obstacle.type.equalsIgnoreCase("rottenEgg")){
                    //System.out.println("get rotten egg bitmap");
                    obs = GameConstants.getBitmapBank().getRottenEgg();
                }
                //System.out.println("draw obstacle  "+obstacle.type+" at x="+obstacle.cX+" abd y="+obstacle.cY);
                canvas.drawBitmap(obs, obstacle.cX,obstacle.cY,null);
                // Handle collison
                if((obstacle.cX - player.pX - GameConstants.getBitmapBank().getPlayerWidth()) <= 20){
                    if(GameConstants.playerGrounded){
                        obstacle.reset();
                        points += getPoint(obstacle.type);
                        if(points <= 0){
                            System.out.println("Game over");
                            // TODO: save score and display game over screen
                            gameState = 2;
                        }
                        else{
                            obstacleSpawed = false;
                        }

                    }
                }

                /*if(obstacle.cX <= -GameConstants.getBitmapBank().getSmallEggWidth()){
                    obstacle.reset();
                    points += 10;
                    obstacleSpawed = false;
                }*/
            }
        }
    }

    private int getPoint(String obstacleType){
        System.out.println("Get points for "+obstacleType);
        if(obstacleType == null){
            return 0;
        }
        switch (obstacleType.toLowerCase()){
            case "rottenegg":
                return -10;
            case "bigegg":
                return 5;
            case "mediumegg":
                return 10;
            case "smallegg":
                return 15;
            default:
                return 0;
        }
    }
}
