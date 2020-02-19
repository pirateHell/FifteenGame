package com.piratehell.fifteengame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                if (!gameField.isGameAlive) gameField.restart();
                else {
                    int minRecord = 0;
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    String q = "SELECT " + DBHelper.COLUMN_RESULT + " FROM " + DBHelper.TABLE_NAME + " ORDER BY " + DBHelper.COLUMN_RESULT + " ASC";
                    Cursor cursor = database.rawQuery(q, null);
                    int i = 0;
                    while (cursor.moveToNext()) {
                        minRecord = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_RESULT));
                        i++;
                        if (i == 5) break;
                    }
                    cursor.close();
                    if (i < 5 || (i >= 5 && minRecord > GameField.turns)) {
                        Intent intent = new Intent(GameActivity.this, WinActivity.class);
                        intent.putExtra("Result", GameField.turns);
                        startActivity(intent);
                        gameField.restart();
                    }
                    else
                        gameField.restart();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameField.isGameAlive) gameField.cancel();
                else Toast.makeText(getApplicationContext(), R.string.btnShuffle, Toast.LENGTH_LONG).show();
            }
        });
    }
}
