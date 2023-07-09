package com.example.crossyroadgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Configuration extends AppCompatActivity {
    private EditText playerNameEditText;
    private RadioGroup difficultyRadioGroup;
    private ImageView playerSpriteImageView;
    private RadioGroup characterSpriteRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        playerNameEditText = findViewById(R.id.editTextPlayerName);
        difficultyRadioGroup = findViewById(R.id.difficulty_radio_group);
        playerSpriteImageView = findViewById(R.id.player_sprite_image_view);
        characterSpriteRadioGroup = findViewById(R.id.character_sprite_group);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = playerNameEditText.getText().toString();
                if (!isValidName(playerName)) {
                    Toast.makeText(Configuration.this,
                            "Please enter a valid player name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedDifficultyId = difficultyRadioGroup.getCheckedRadioButtonId();
                if (selectedDifficultyId == -1) {
                    Toast.makeText(Configuration.this,
                            "Please select a difficulty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedSpriteId = characterSpriteRadioGroup.getCheckedRadioButtonId();
                if (selectedSpriteId == -1) {
                    Toast.makeText(Configuration.this,
                            "Please select a character",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                RadioButton selectedDifficultyRadioButton = findViewById(selectedDifficultyId);
                String selectedDifficulty = selectedDifficultyRadioButton.getText().toString();
                int difficulty = setDifficulty(selectedDifficulty);

                RadioButton selectedSpriteRadioButton = findViewById(selectedSpriteId);
                String selectedSprite = selectedSpriteRadioButton.getText().toString();
                int[] currentSpriteIdArray = new int[5];
                currentSpriteIdArray[0] = setSprite(selectedSprite);
                currentSpriteIdArray[1] = setAltSprite(selectedSprite);

                Intent intent = new Intent(Configuration.this, GameScreen.class);
                intent.putExtra("player_name", playerName);
                intent.putExtra("selected_difficulty", difficulty);
                intent.putExtra("player_sprite_id_array", currentSpriteIdArray);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public static int setDifficulty(String selectedDifficulty) {
        int difficulty;
        switch (selectedDifficulty) {
        case "Hard":
            difficulty = 5;
            break;
        case "Medium":
            difficulty = 3;
            break;
        default:
            difficulty = 1;
            break;
        }
        return difficulty;
    }


    public int setSprite(String selectedSprite) {
        int spriteId;
        switch (selectedSprite) {
        case "Kangaroo":
            spriteId = R.drawable.kangaroo;
            break;
        case "Camel":
            spriteId = R.drawable.camel;
            break;
        default:
            spriteId = R.drawable.vulture;
            break;
        }
        return spriteId;
    }
    public int setAltSprite(String selectedSprite) {
        int altSpriteId;
        switch (selectedSprite) {
        case "Kangaroo":
            altSpriteId = R.drawable.kangaroo_right;
            break;
        case "Camel":
            altSpriteId = R.drawable.camel_right;
            break;
        default:
            altSpriteId = R.drawable.vulture_right;
            break;
        }
        return altSpriteId;
    }


    public static boolean isValidName(String playerName) {
        return !(playerName == null || playerName.trim().length() == 0);
    }

}