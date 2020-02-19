package com.piratehell.fifteengame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {
    TextView winMessageHeader;
    EditText editName;
    Button sendResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        winMessageHeader = findViewById(R.id.win_message_header2);
        editName = findViewById(R.id.edit_name);
        sendResult = findViewById(R.id.send_result);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        final int result = intent.getIntExtra("Result", -1);
        winMessageHeader.setText("" + result);

        sendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                if (name.equals("")) name = "Anonim";

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.COLUMN_NAME, name);
                contentValues.put(DBHelper.COLUMN_RESULT, result);

                database.insert(DBHelper.TABLE_NAME, null, contentValues);
                dbHelper.close();

                finish();
            }
        });
    }
}
