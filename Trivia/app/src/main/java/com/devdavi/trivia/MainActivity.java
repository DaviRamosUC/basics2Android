package com.devdavi.trivia;

import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.devdavi.trivia.data.Repository;
import com.devdavi.trivia.databinding.ActivityMainBinding;
import com.devdavi.trivia.model.Question;
import com.devdavi.trivia.util.Prefs;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questionList;

    private int scoreValue = 0;
    private Prefs prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        prefs = new Prefs(MainActivity.this);

        //Retrieve the currentState
        currentQuestionIndex = prefs.getState();

        questionList = new Repository().getQuestions(questionArrayList -> {
            updateQuestion();
        });

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
            updateQuestion();
        });

        binding.buttonPrevious.setOnClickListener(view -> {
            if (currentQuestionIndex > 0){
                currentQuestionIndex--;
                updateQuestion();
            }
        });

        binding.buttonTrue.setOnClickListener(view -> {
            checkAnswer(true);
        });

        binding.buttonFalse.setOnClickListener(view -> {
            checkAnswer(false);
        });
        updateHighScore();
    }

    @Override
    protected void onPause() {
        prefs.saveHighestScore(scoreValue);
        prefs.setState(currentQuestionIndex);
        super.onPause();
    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswer();
        int snackMessageId = 0;
        if (userChoseCorrect == answer) {
            fadeAnimation();
            addPoints();
            snackMessageId = R.string.correct_answer;
        } else {
            snackMessageId = R.string.incorrect;
            shakeAnimation();
            removePoints();
            updateQuestion();
        }
        binding.buttonNext.callOnClick();
        prefs.saveHighestScore(scoreValue);
        updateHighScore();
        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void updateHighScore(){
        binding.highScore.setText(String.format(getString(R.string.actual_high_score), prefs.getHighestScore()));
    }

    private void removePoints() {
        if (scoreValue >= 100) {
            scoreValue -= 100;
        }else{
            scoreValue = 0;
        }
    }

    private void addPoints() {
        scoreValue += 100;
    }

    private void updateQuestion() {
        updateCounter();
        String question = questionList.get(currentQuestionIndex).getQuestion();
        binding.questionTextView.setText(question);
        binding.actualScore.setText(String.format(getString(R.string.actual_scored), scoreValue));
    }

    private void updateCounter() {
        binding.textViewOutOf.setText(String.format(getString(R.string.text_formated), currentQuestionIndex, questionList.size()));
    }

    private void fadeAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);
        binding.cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}