package com.devdavi.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowGuess extends AppCompatActivity {
    private TextView showGuessTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        showGuessTextview = findViewById(R.id.received_textview);
        Bundle extra = getIntent().getExtras();


        if (extra != null) {
            showGuessTextview.setText(extra.getString("guess"));
            Log.d("nameExtra", "Name: "+ extra.getString("name"));
            Log.d("ageExtra", "Age: "+ extra.getInt("age"));
        }

        showGuessTextview.setOnClickListener(view -> {
            Intent intent = getIntent();
            intent.putExtra("message_back", "From second activity");
            setResult(RESULT_OK, intent);
            finish();
        });

//        String value = getIntent().getStringExtra("guess");
//        if (value != null) {
//            Log.d("Stuff", " " + getIntent().getStringExtra("name"));
//            textView.setText(value);
//        }

    }
}