package com.example.dinodash.entity;

import com.example.dinodash.game.GameConstants;
import com.example.dinodash.utils.Helper;

public class Player {
    public int pX, pY, pYInitial, currentFrame, velocity;

    public Player(){
        pX = Helper.SCREEN_WIDTH / 3 - GameConstants.getBitmapBank().getPlayerWidth();
        pYInitial = Helper.SCREEN_HEIGHT - GameConstants.getBitmapBank().getPlayerHeight() - 50;
        pY = Helper.SCREEN_HEIGHT - GameConstants.getBitmapBank().getPlayerHeight() -50;
        currentFrame = 0;
        velocity = 0;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpY() {
        return pY;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }

    public int getpYInitial() {
        return pYInitial;
    }

    public void setpYInitial(int pYInitial) {
        this.pYInitial = pYInitial;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
