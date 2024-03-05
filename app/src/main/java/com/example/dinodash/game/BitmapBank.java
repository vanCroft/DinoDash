package com.example.dinodash.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.example.dinodash.R;
import com.example.dinodash.utils.Helper;

public class BitmapBank {
    Bitmap background;
    Bitmap[] player = new Bitmap[4];
    Bitmap[] playerJump = new Bitmap[2];
    Bitmap[] playerDead = new Bitmap[2];
    Bitmap smallEgg;
    Bitmap mediumEgg;
    Bitmap bigEgg;
    Bitmap rottenEgg;

    public BitmapBank(Resources resources){
        background = BitmapFactory.decodeResource(resources, R.drawable.dinodashbg);
        background = scaleBackgrounImage(background);
        // player
        player[0] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        player[1] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        player[2] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        player[3] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        playerJump[0] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        playerJump[1] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        playerDead[0] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        playerDead[1] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        smallEgg = BitmapFactory.decodeResource(resources, R.drawable.egg);
        mediumEgg = BitmapFactory.decodeResource(resources, R.drawable.egg);
        bigEgg = BitmapFactory.decodeResource(resources, R.drawable.egg);
        rottenEgg = BitmapFactory.decodeResource(resources,R.drawable.egg);
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

    public Bitmap getPlayer(int frame) throws Exception{
        if(player == null){
            throw new Exception("No Player provided");
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

    public Bitmap getPlayerJump(int frame){
        /*if(playerJump == null){
            throw new Exception("No playerJump provided");
        }*/
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

    public Bitmap getPlayerDead(int frame){
        return playerDead[frame];
    }

    public Bitmap getSmallEgg(){
        return smallEgg;
    }

    public int getSmallEggWidth(){
        if(smallEgg != null){
            return smallEgg.getWidth();
        }
        return 0;
    }

    public int getSmallEggHeight(){
        if(smallEgg != null){
            return smallEgg.getHeight();
        }
        return 0;
    }

    public Bitmap getMediumEgg(){
        return mediumEgg;
    }

    public int getMediumEggWidth(){
        if(mediumEgg != null){
            return mediumEgg.getWidth();
        }
        return 0;
    }

    public int getMediumEggHeight(){
        if(mediumEgg != null){
            return mediumEgg.getHeight();
        }
        return 0;
    }

    public Bitmap getBigEgg(){
        return bigEgg;
    }

    public int getBigEggWidth(){
        if(bigEgg != null){
            return  bigEgg.getWidth();
        }
        return 0;
    }

    public int getBigEggHeight(){
        if(bigEgg != null){
            return  bigEgg.getHeight();
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
