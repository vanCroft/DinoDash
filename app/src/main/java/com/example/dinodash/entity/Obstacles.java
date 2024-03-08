package com.example.dinodash.entity;

import com.example.dinodash.game.GameConstants;
import com.example.dinodash.utils.Helper;

import java.util.Random;

public class Obstacles {
    public int cX, cY;
    public String type;
    public int velocity;
    public int width;
    public int points;
    Random random;

    public Obstacles(String type){
        this.type = type;
        cX = Helper.SCREEN_WIDTH +1000;
        cY = Helper.SCREEN_HEIGHT;
        velocity = 0;
        width = 0;
        points = 0;
        random = new Random();

        if(type.equalsIgnoreCase("egg")){
            cY -= GameConstants.getBitmapBank().getEggHeight();
            velocity = GameConstants.OBSTACLE_VELOCITY + 14 + random.nextInt(5);
            width = GameConstants.getBitmapBank().getEggWidth();
        }
        else if(type.equalsIgnoreCase("rottenEgg")){
            cY -= GameConstants.getBitmapBank().getRottenHeight();
            velocity = GameConstants.OBSTACLE_VELOCITY + 15 + random.nextInt(5);
            width = GameConstants.getBitmapBank().getRottenEggWidth();
        }
    }

    public void reset(){
        cX = Helper.SCREEN_WIDTH + 300;
        if(type.equalsIgnoreCase("egg")){
            this.velocity = GameConstants.OBSTACLE_VELOCITY + 14 + random.nextInt(5);
        }
        else if(type.equalsIgnoreCase("rottenEgg")){
            this.velocity = GameConstants.OBSTACLE_VELOCITY + 15 + random.nextInt(5);
        }
    }
}
