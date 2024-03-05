package com.example.dinodash.entity;

import com.example.dinodash.game.GameConstants;

public class BigEgg extends Obstacles{
    public BigEgg(){
        super("bigEgg");
        cY -= GameConstants.getBitmapBank().getBigEggHeight();
        velocity = GameConstants.OBSTACLE_VELOCITY + 10 + random.nextInt(5);
        width = GameConstants.getBitmapBank().getBigEggWidth();
        points = 5;
    }
    @Override
    public void reset() {
        velocity = GameConstants.OBSTACLE_VELOCITY +10 + random.nextInt(5);
    }
}
