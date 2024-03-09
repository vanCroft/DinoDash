package com.example.dinodash.ui.screens;

import android.content.Intent;
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
        int bestScore = getIntent().getIntExtra("bestScore",0);

        scoreView = findViewById(R.id.gamePoints);
        bestScoreView = findViewById(R.id.gamePersonalBest);

        scoreView.setText(" "+points);
        bestScoreView.setText(" "+bestScore);
    }

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        Intent intent =  new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
