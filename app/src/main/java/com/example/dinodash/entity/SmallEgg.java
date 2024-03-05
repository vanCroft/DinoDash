package com.example.dinodash.entity;

import com.example.dinodash.game.GameConstants;
import com.example.dinodash.utils.Helper;

public class SmallEgg extends Obstacles{
    public SmallEgg(){
        super("smallEgg");
        cY -= GameConstants.getBitmapBank().getSmallEggHeight();
        velocity = GameConstants.OBSTACLE_VELOCITY + 14 + random.nextInt(5);
        width = GameConstants.getBitmapBank().getSmallEggWidth();
        points = -10;
    }
    @Override
    public void reset() {
        cX = Helper.SCREEN_WIDTH + 300;
        this.velocity = GameConstants.OBSTACLE_VELOCITY +14 + random.nextInt(5);
    }
}
