package com.devdavi.trivia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.devdavi.trivia.data.Repository;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Repository().getQuestions();
    }
}