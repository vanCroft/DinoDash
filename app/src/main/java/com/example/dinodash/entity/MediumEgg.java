package com.example.dinodash.entity;

import com.example.dinodash.game.GameConstants;

public class MediumEgg extends Obstacles{
    public MediumEgg(){
        super("mediumEgg");
        cY -= GameConstants.getBitmapBank().getMediumEggHeight();
        velocity = GameConstants.OBSTACLE_VELOCITY + 16 + random.nextInt(5);
        width = GameConstants.getBitmapBank().getMediumEggWidth();
        points = 10;
    }

    @Override
    public void reset() {
        velocity = GameConstants.OBSTACLE_VELOCITY +16 + random.nextInt(5);
    }
}
