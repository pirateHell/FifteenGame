package com.piratehell.fifteengame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStartGame, btnAboutGame, btnQuitGame;
    View.OnClickListener ocl;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartGame = findViewById(R.id.btnStartGame);
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
                    case R.id.btnAboutGame:
                        //Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, AboutGameActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnQuitGame:
                        //Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
            }
        };
        btnStartGame.setOnClickListener(ocl);
        btnAboutGame.setOnClickListener(ocl);
        btnQuitGame.setOnClickListener(ocl);
    }
}
