package com.devdavi.trivia;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.devdavi.trivia.data.Repository;
import com.devdavi.trivia.model.Question;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Question> questions = new Repository().getQuestions(questionArrayList -> {
            Log.d("MAIN", "onCreate: " + questionArrayList);
        });

    }
}