package com.example.dinodash.ui.screens;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dinodash.game.GameConstants;
import com.example.dinodash.game.GameView;

public class GameActivity extends AppCompatActivity {
    GameView gameView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        GameConstants.initialize(this);

        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        gameView = new GameView(this);

        layout.addView(gameView);
        setContentView(layout);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(gameView != null){
            gameView.pause();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(gameView != null){
            gameView.resume();
        }
    }

     @Override
     public void onDestroy(){
        super.onDestroy();
        System.out.println("GameActivity destroyed");
     }
}
