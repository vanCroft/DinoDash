package com.example.dinodash.game;

import android.content.Context;

import com.example.dinodash.utils.Helper;

public class GameConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    public static int gravity;
    public static int JUMP_VELOCITY;
    public static int OBSTACLE_VELOCITY;
    public static Context gameActivityContext;
    static boolean playerGrounded;

    public static void initialize(Context context){
        Helper.setScreenSize(context);
        GameConstants.gameActivityContext = context;
        bitmapBank = new BitmapBank(context.getResources());
        GameConstants.gravity = 3;
        GameConstants.JUMP_VELOCITY = -40;
        GameConstants.OBSTACLE_VELOCITY = 5;
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
