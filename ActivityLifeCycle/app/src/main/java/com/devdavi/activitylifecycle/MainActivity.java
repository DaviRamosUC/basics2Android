package com.devdavi.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.devdavi.activitylifecycle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Button showGuess;
    private EditText enterGuess;
    private final int REQUEST_CODE = 2;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        showGuess = binding.buttonGuess;
        enterGuess = binding.guessField;

        showGuess.setOnClickListener(view -> {
            if (!binding.guessField.getText().toString().isEmpty()) {
                Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                intent.putExtra("guess", binding.guessField.getText().toString().trim());
                intent.putExtra("name", "bond");
                intent.putExtra("age", 34);
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(MainActivity.this, "Enter guess", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            assert data != null;
            String message = data.getStringExtra("message_back");
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

    }
}