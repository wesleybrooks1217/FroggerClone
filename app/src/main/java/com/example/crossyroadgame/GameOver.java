package com.example.crossyroadgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    // on the below line we are creating a variable.
    private Button closeApplicationBtn;
    private Button restartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("score");
        setContentView(R.layout.activity_game_over);
        String gameText = "";
        boolean win = extras.getBoolean("win");
        if (win) {
            gameText = "Congrats, you win! Your high score was " + score;
        } else {
            gameText = "Game Over. Your score was: " + score;
        }
        ((TextView) findViewById(R.id.idTVHeading)).setText(gameText);

        // on below line we are initializing variables with ids.
        closeApplicationBtn = findViewById(R.id.idBtnCloseApplication);
        restartBtn = findViewById(R.id.idBtnRestart);

        // on below line we are adding click listener for our button
        closeApplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, Configuration.class);
                startActivity(intent);
            }
        });
    }
}
