package com.devdavi.trivia;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.devdavi.trivia.data.Repository;
import com.devdavi.trivia.databinding.ActivityMainBinding;
import com.devdavi.trivia.model.Question;
import com.google.android.material.snackbar.Snackbar;

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
            checkAnswer(true);
        });

        binding.buttonFalse.setOnClickListener(view -> {
            checkAnswer(false);
        });

    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswer();
        int snackMessageId = 0;
        if (userChoseCorrect == answer) {
            binding.buttonNext.callOnClick();
            snackMessageId = R.string.correct_answer;
        } else {
            snackMessageId = R.string.incorrect;
            shakeAnimation();
            updateQuestion();
        }
        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void updateQuestion() {
        updateCounter();
        String question = questionList.get(currentQuestionIndex).getQuestion();
        binding.questionTextView.setText(question);
    }

    private void updateCounter() {
        binding.textViewOutOf.setText(String.format(getString(R.string.text_formated), currentQuestionIndex, questionList.size()));
    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);
        binding.cardView.setAnimation(shake);
    }
}