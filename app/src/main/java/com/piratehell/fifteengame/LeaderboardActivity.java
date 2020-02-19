package com.piratehell.fifteengame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class LeaderboardActivity extends AppCompatActivity {
    ListView leaderboardList;
    Button btnClear;
    Button btnBack;
    DBHelper dbHelper;
    String[] usersArray = {"<empty>", "<empty>", "<empty>", "<empty>", "<empty>"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardList = findViewById(R.id.leaderboard_list);
        btnClear = findViewById(R.id.btnClear);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DBHelper(this);
        updateLeaderboard();

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnClear:
                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        database.delete(DBHelper.TABLE_NAME, null, null);
                        for (int i = 0; i < usersArray.length; i++) {
                            usersArray[i] = "<empty>";
                        }

                        double r = Math.floor(Math.random() * 100); // шанс пасхалки - 1%
                        if (r == 75.0) {
                            // Пасхалка же!
                            Toast.makeText(getApplicationContext(), "Пасхалка же! (easter egg)", Toast.LENGTH_LONG).show();
                            String[] name = {"Your Batya", "Pivo aka Bear", "Satan", "INTERNET_ERROR", "pirateHell"};
                            int[] result = {1972, 777, 666, 404, 0};
                            ContentValues contentValues = new ContentValues();

                            for (int i = 0; i < 5; i++) {
                                contentValues.put(DBHelper.COLUMN_NAME, name[i]);
                                contentValues.put(DBHelper.COLUMN_RESULT, result[i]);
                                database.insert(DBHelper.TABLE_NAME, null, contentValues);
                            }
                        }
                        updateLeaderboard();
                        break;
                    case R.id.btnBack:
                        finish();
                        break;
                }
            }
        };
        btnClear.setOnClickListener(ocl);
        btnBack.setOnClickListener(ocl);
    }

    public void updateLeaderboard() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String q = "SELECT * FROM " + DBHelper.TABLE_NAME + " ORDER BY " + DBHelper.COLUMN_RESULT + " ASC";
        Cursor cursor = database.rawQuery(q, null);
        int i = 0;
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
            String result = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RESULT));
            usersArray[i] = (i + 1) + ") " + name + " (" + result + ")";
            i++;
            if (i == usersArray.length) break;
        }
        cursor.close();

        ArrayAdapter<String> usersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usersArray);
        leaderboardList.setAdapter(usersAdapter);
    }
}
