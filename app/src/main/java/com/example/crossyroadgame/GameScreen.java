package com.example.crossyroadgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class GameScreen extends AppCompatActivity {


    private Player player;
    private GameMap gameMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("player_name");
        int playerDifficulty = extras.getInt("selected_difficulty");
        int[] playerSpriteIdArray = extras.getIntArray("player_sprite_id_array");
        gameMap = new GameMap(this, playerName, playerDifficulty, playerSpriteIdArray);

        setContentView(gameMap);

    }


}