package com.example.dinodash.game;

import android.content.Context;

import com.example.dinodash.utils.Helper;

public class GameConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    public static int gravity;
    public static int JUMP_VELOCITY;
    public static int OBSTACLE_VELOCITY;
    public static long PLAY_TIME = 60; // Max playing time in seconds
    public static Context gameActivityContext;
    static boolean playerGrounded;

    public static void initialize(Context context){
        Helper.setScreenSize(context);
        GameConstants.gameActivityContext = context;
        bitmapBank = new BitmapBank(context.getResources());
        GameConstants.JUMP_VELOCITY = -40;
        GameConstants.OBSTACLE_VELOCITY = 15;
        GameConstants.playerGrounded = true;
        gameEngine = new GameEngine();
    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    public static GameEngine getGameEngine(){
        return gameEngine;
    }

}
