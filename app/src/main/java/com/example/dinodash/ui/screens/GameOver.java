package com.example.dinodash.ui.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dinodash.R;

public class GameOver extends AppCompatActivity {
    TextView scoreView, bestScoreView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int points = getIntent().getIntExtra("points",0);
        SharedPreferences pref = getSharedPreferences("DinoDash",0);
        int bestScore = pref.getInt("bestScore",0);
        if(points > bestScore){
            SharedPreferences.Editor editor = pref.edit();
            bestScore = points;
            editor.putInt("bestScore",bestScore);
            editor.commit();
        }
        scoreView = findViewById(R.id.gamePoints);
        bestScoreView = findViewById(R.id.gamePersonalBest);

        scoreView.setText(" "+points);
        bestScoreView.setText(" "+bestScore);
    }

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }
}
