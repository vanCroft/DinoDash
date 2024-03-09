package com.example.dinodash.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.example.dinodash.R;
import com.example.dinodash.utils.Helper;

public class BitmapBank {
    Bitmap background;
    Bitmap[] player = new Bitmap[24];
    Bitmap[] playerJump = new Bitmap[30];
    Bitmap egg;
    Bitmap rottenEgg;
    Bitmap tree;


    public BitmapBank(Resources resources){
        background = BitmapFactory.decodeResource(resources, R.drawable.dino_game_bg);
        background = scaleBackgrounImage(background);
        // player
        /*for(int i =0; i<player.length;i++){
            player[i] = BitmapFactory.decodeResource(resources, R.drawable.dino);
        }*/
        player[0] = BitmapFactory.decodeResource(resources, R.drawable.run_000);
        player[1] = BitmapFactory.decodeResource(resources, R.drawable.run_001);
        player[2] = BitmapFactory.decodeResource(resources, R.drawable.run_002);
        player[3] = BitmapFactory.decodeResource(resources, R.drawable.run_003);
        player[4] = BitmapFactory.decodeResource(resources, R.drawable.run_004);
        player[5] = BitmapFactory.decodeResource(resources, R.drawable.run_005);
        player[6] = BitmapFactory.decodeResource(resources, R.drawable.run_006);
        player[7] = BitmapFactory.decodeResource(resources, R.drawable.run_007);
        player[8] = BitmapFactory.decodeResource(resources, R.drawable.run_008);
        player[9] = BitmapFactory.decodeResource(resources, R.drawable.run_009);
        player[10] = BitmapFactory.decodeResource(resources, R.drawable.run_010);
        player[11] = BitmapFactory.decodeResource(resources, R.drawable.run_011);
        player[12] = BitmapFactory.decodeResource(resources, R.drawable.run_012);
        player[13] = BitmapFactory.decodeResource(resources, R.drawable.run_013);
        player[14] = BitmapFactory.decodeResource(resources, R.drawable.run_014);
        player[15] = BitmapFactory.decodeResource(resources, R.drawable.run_015);
        player[16] = BitmapFactory.decodeResource(resources, R.drawable.run_016);
        player[17] = BitmapFactory.decodeResource(resources, R.drawable.run_017);
        player[18] = BitmapFactory.decodeResource(resources, R.drawable.run_018);
        player[19] = BitmapFactory.decodeResource(resources, R.drawable.run_019);
        player[20] = BitmapFactory.decodeResource(resources, R.drawable.run_020);
        player[21] = BitmapFactory.decodeResource(resources, R.drawable.run_021);
        player[22] = BitmapFactory.decodeResource(resources, R.drawable.run_022);
        player[23] = BitmapFactory.decodeResource(resources, R.drawable.run_023);
        // Jumping player
        playerJump[0] = BitmapFactory.decodeResource(resources, R.drawable.jump_000);
        playerJump[1] = BitmapFactory.decodeResource(resources, R.drawable.jump_001);
        playerJump[2] = BitmapFactory.decodeResource(resources, R.drawable.jump_002);
        playerJump[3] = BitmapFactory.decodeResource(resources, R.drawable.jump_003);
        playerJump[4] = BitmapFactory.decodeResource(resources, R.drawable.jump_004);
        playerJump[5] = BitmapFactory.decodeResource(resources, R.drawable.jump_005);
        playerJump[6] = BitmapFactory.decodeResource(resources, R.drawable.jump_006);
        playerJump[7] = BitmapFactory.decodeResource(resources, R.drawable.jump_007);
        playerJump[8] = BitmapFactory.decodeResource(resources, R.drawable.jump_008);
        playerJump[9] = BitmapFactory.decodeResource(resources, R.drawable.jump_009);
        playerJump[10] = BitmapFactory.decodeResource(resources, R.drawable.jump_010);
        playerJump[11] = BitmapFactory.decodeResource(resources, R.drawable.jump_011);
        playerJump[12] = BitmapFactory.decodeResource(resources, R.drawable.jump_012);
        playerJump[13] = BitmapFactory.decodeResource(resources, R.drawable.jump_013);
        playerJump[14] = BitmapFactory.decodeResource(resources, R.drawable.jump_014);
        playerJump[15] = BitmapFactory.decodeResource(resources, R.drawable.jump_015);
        playerJump[16] = BitmapFactory.decodeResource(resources, R.drawable.jump_016);
        playerJump[17] = BitmapFactory.decodeResource(resources, R.drawable.jump_017);
        playerJump[18] = BitmapFactory.decodeResource(resources, R.drawable.jump_018);
        playerJump[19] = BitmapFactory.decodeResource(resources, R.drawable.jump_019);
        playerJump[20] = BitmapFactory.decodeResource(resources, R.drawable.jump_020);
        playerJump[21] = BitmapFactory.decodeResource(resources, R.drawable.jump_021);
        playerJump[22] = BitmapFactory.decodeResource(resources, R.drawable.jump_022);
        playerJump[23] = BitmapFactory.decodeResource(resources, R.drawable.jump_023);
        playerJump[24] = BitmapFactory.decodeResource(resources, R.drawable.jump_024);
        playerJump[25] = BitmapFactory.decodeResource(resources, R.drawable.jump_025);
        playerJump[26] = BitmapFactory.decodeResource(resources, R.drawable.jump_026);
        playerJump[27] = BitmapFactory.decodeResource(resources, R.drawable.jump_027);
        playerJump[28] = BitmapFactory.decodeResource(resources, R.drawable.jump_028);
        playerJump[29] = BitmapFactory.decodeResource(resources, R.drawable.jump_029);
        // Egg
        egg = BitmapFactory.decodeResource(resources, R.drawable.ei);
        // Rotten egg
        rottenEgg = BitmapFactory.decodeResource(resources,R.drawable.rotten_egg);
        // Tree
        tree = BitmapFactory.decodeResource(resources, R.drawable.tree);
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

    public Bitmap getTree() {
        return tree;
    }

    public int getTreeWidth(){
        if(tree != null){
            return tree.getWidth();
        }
        return 0;
    }

    public int getTreeHeight(){
        if(tree != null){
            return tree.getHeight();
        }
        return 0;
    }

    public Bitmap scaleBackgrounImage(@NonNull Bitmap image){
        float ratio = getBackgroundWidth() / getBackgroundHeight();
        int scaledWidth = (int) ratio * Helper.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(image,scaledWidth, Helper.SCREEN_HEIGHT, false);
    }
}
