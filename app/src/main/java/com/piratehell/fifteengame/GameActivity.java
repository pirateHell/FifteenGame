package com.piratehell.fifteengame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    Button[][] fieldUI = new Button[4][4];
    Button btnBack, btnShuffle, btnCancel;
    TextView tvTurns;
    View.OnClickListener ocl;
    GameField gameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fieldUI[0][0] = findViewById(R.id.btn00);
        fieldUI[0][1] = findViewById(R.id.btn01);
        fieldUI[0][2] = findViewById(R.id.btn02);
        fieldUI[0][3] = findViewById(R.id.btn03);
        fieldUI[1][0] = findViewById(R.id.btn10);
        fieldUI[1][1] = findViewById(R.id.btn11);
        fieldUI[1][2] = findViewById(R.id.btn12);
        fieldUI[1][3] = findViewById(R.id.btn13);
        fieldUI[2][0] = findViewById(R.id.btn20);
        fieldUI[2][1] = findViewById(R.id.btn21);
        fieldUI[2][2] = findViewById(R.id.btn22);
        fieldUI[2][3] = findViewById(R.id.btn23);
        fieldUI[3][0] = findViewById(R.id.btn30);
        fieldUI[3][1] = findViewById(R.id.btn31);
        fieldUI[3][2] = findViewById(R.id.btn32);
        fieldUI[3][3] = findViewById(R.id.btn33);
        tvTurns = findViewById(R.id.tvTurns);
        gameField = new GameField(fieldUI, tvTurns, getApplicationContext());

        btnBack = findViewById(R.id.btnBack);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnCancel = findViewById(R.id.btnCancel);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameField.restart();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameField.cancel();
            }
        });
    }
}
