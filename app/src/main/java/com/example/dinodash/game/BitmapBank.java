package com.example.dinodash.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.example.dinodash.R;
import com.example.dinodash.utils.Helper;

public class BitmapBank {
    Bitmap background;
    Bitmap[] player = new Bitmap[12];
    Bitmap[] playerJump = new Bitmap[12];
    Bitmap playerDead;
    Bitmap egg;
    Bitmap rottenEgg;


    public BitmapBank(Resources resources){
        background = BitmapFactory.decodeResource(resources, R.drawable.game_bg);
        background = scaleBackgrounImage(background);
        // player
        player[0] = BitmapFactory.decodeResource(resources, R.drawable.dino1);
        player[1] = BitmapFactory.decodeResource(resources, R.drawable.dino1);
        player[2] = BitmapFactory.decodeResource(resources, R.drawable.dino1);
        player[3] = BitmapFactory.decodeResource(resources, R.drawable.dino1);
        player[4] = BitmapFactory.decodeResource(resources, R.drawable.dino1);
        player[5] = BitmapFactory.decodeResource(resources, R.drawable.dino1);
        player[6] = BitmapFactory.decodeResource(resources, R.drawable.dino2);
        player[7] = BitmapFactory.decodeResource(resources, R.drawable.dino2);
        player[8] = BitmapFactory.decodeResource(resources, R.drawable.dino2);
        player[9] = BitmapFactory.decodeResource(resources, R.drawable.dino2);
        player[10] = BitmapFactory.decodeResource(resources, R.drawable.dino2);
        player[11] = BitmapFactory.decodeResource(resources, R.drawable.dino2);
        // Jumping player
        playerJump[0] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[1] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[2] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[3] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[4] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[5] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[6] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[7] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[8] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[9] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[10] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        playerJump[11] = BitmapFactory.decodeResource(resources, R.drawable.dino_jump);
        // Dead player
        playerDead = BitmapFactory.decodeResource(resources, R.drawable.dino);
        // Egg
        egg = BitmapFactory.decodeResource(resources, R.drawable.egg);
        // Rotten egg
        rottenEgg = BitmapFactory.decodeResource(resources,R.drawable.rotten_egg);
    }

    public Bitmap getBackground(){
        return background;
    }

    public int getBackgroundWidth(){
        if(background != null){
            return background.getWidth();
        }
        return 0;
    }

    public int getBackgroundHeight(){
        if(background != null){
            return background.getHeight();
        }
        return 0;
    }

    public Bitmap[] getPlayer() {
        return player;
    }

    public Bitmap getPlayer(int frame){
        if (frame >= player.length){
            frame = 0;
        }
        return player[frame];
    }

    public int getPlayerWidth(){
        if(player == null){
            return 0;
        }
        return player[0].getWidth();
    }

    public int getPlayerHeight(){
        if(player == null){
            return 0;
        }
        return player[0].getHeight();
    }

    public Bitmap[] getPlayerJump(){
        return playerJump;
    }

    public Bitmap getPlayerJump(int frame){
        if(frame >= playerJump.length){
            frame = 0;
        }
        return playerJump[frame];
    }

    public int getPlayerJumpWidth(){
        if (playerJump == null){
            return 0;
        }
        return playerJump[0].getWidth();
    }

    public int getPlayerJumpHeight(){
        if(playerJump == null){
            return 0;
        }
        return playerJump[0].getHeight();
    }

    public Bitmap getPlayerDead(){
        return playerDead;
    }

    public Bitmap getEgg(){
        return egg;
    }

    public int getEggWidth(){
        if(egg != null){
            return egg.getWidth();
        }
        return 0;
    }

    public int getEggHeight(){
        if(egg != null){
            return egg.getHeight();
        }
        return 0;
    }

    public Bitmap getRottenEgg(){
        return rottenEgg;
    }

    public int getRottenEggWidth(){
        if(rottenEgg != null){
            return rottenEgg.getWidth();
        }
        return 0;
    }

    public int getRottenHeight(){
        if(rottenEgg != null){
            return rottenEgg.getHeight();
        }
        return 0;
    }

    public Bitmap scaleBackgrounImage(@NonNull Bitmap image){
        float ratio = getBackgroundWidth() / getBackgroundHeight();
        int scaledWidth = (int) ratio * Helper.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(image,scaledWidth, Helper.SCREEN_HEIGHT, false);
    }
}
