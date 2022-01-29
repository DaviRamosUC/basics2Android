package com.devdavi.trivia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.devdavi.trivia.data.Repository;
import com.devdavi.trivia.databinding.ActivityMainBinding;
import com.devdavi.trivia.model.Question;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questionList = new Repository().getQuestions(questionArrayList -> {
            updateQuestion();
        });

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
            updateQuestion();
        });

        binding.buttonTrue.setOnClickListener(view -> {

        });

        binding.buttonFalse.setOnClickListener(view -> {

        });

    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getQuestion();
        binding.questionTextView.setText(question);
    }
}