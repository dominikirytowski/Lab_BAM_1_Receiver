package com.example.receiver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.getButton);

        button.setOnClickListener(v -> fetchData());
    }

    @SuppressLint("Range")
    private void fetchData() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor cursor = getContentResolver()
                    .query(Uri.parse("content://com.example.lab_bam.provider/logcatentry"), null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Log.i("ContentProvider",
                            "Id: " + cursor.getString(cursor.getColumnIndex("id")) + " " +
                                    "Username: " + cursor.getString(cursor.getColumnIndex("username")) + " " +
                                    "Number: " + cursor.getInt(cursor.getColumnIndex("number")));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }

    }
}