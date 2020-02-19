package com.piratehell.fifteengame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStartGame, btnUsersBoard, btnAboutGame, btnQuitGame;
    View.OnClickListener ocl;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnUsersBoard = findViewById(R.id.btnUsersBoard);
        btnAboutGame = findViewById(R.id.btnAboutGame);
        btnQuitGame = findViewById(R.id.btnQuitGame);
        ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.btnStartGame:
                        intent = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnUsersBoard:
                        intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnAboutGame:
                        intent = new Intent(MainActivity.this, AboutGameActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnQuitGame:
                        finish();
                        break;
                }
            }
        };
        btnStartGame.setOnClickListener(ocl);
        btnUsersBoard.setOnClickListener(ocl);
        btnAboutGame.setOnClickListener(ocl);
        btnQuitGame.setOnClickListener(ocl);
    }
}
