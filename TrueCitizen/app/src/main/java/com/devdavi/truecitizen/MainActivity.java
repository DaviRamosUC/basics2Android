package com.devdavi.truecitizen;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.devdavi.truecitizen.databinding.ActivityMainBinding;
import com.devdavi.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        updateQuestion();
        binding.trueButton.setOnClickListener(view -> checkAnswer(true));
        binding.falseButton.setOnClickListener(view -> checkAnswer(false));

        binding.nextButton.setOnClickListener(view -> {
            if (currentQuestionIndex < questionBank.length - 1) {
                currentQuestionIndex++;
                updateQuestion();
            }
        });
        binding.prevButton.setOnClickListener(view -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                updateQuestion();
            }
        });
    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answerIsCorrect = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if (answerIsCorrect == userChoseCorrect) {
            messageId = R.string.correct_answer;
        } else {
            messageId = R.string.wrong_answer;
        }

        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void updateQuestion() {
        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }
}