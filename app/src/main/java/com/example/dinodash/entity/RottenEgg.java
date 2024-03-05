package com.example.dinodash.entity;

import com.example.dinodash.game.GameConstants;

public class RottenEgg extends Obstacles{
    public RottenEgg(){
        super("rottenEg");
    }
    @Override
    public void reset() {
        velocity = GameConstants.OBSTACLE_VELOCITY +15 + random.nextInt(5);
    }
}
