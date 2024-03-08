package com.example.dinodash.entity;

public class BackgroundImage {
    private int backgroundImageX;
    private int backgroundImageY;
    private int backgroundImageVelocity;

    public BackgroundImage(){
        backgroundImageX = 0;
        backgroundImageY = 0;
        backgroundImageVelocity = 3;
    }

    public int getBackgroundImageX() {
        return backgroundImageX;
    }

    public void setBackgroundImageX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    public int getBackgroundImageY() {
        return backgroundImageY;
    }

    public void setGetBackgroundImageY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    public int getBackgroundImageVelocity() {
        return backgroundImageVelocity;
    }

    public void seBackgroundImageVelocity(int backgroundImageVelocity) {
        this.backgroundImageVelocity = backgroundImageVelocity;
    }
}
