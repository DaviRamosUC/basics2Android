package com.devdavi.sprefs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.devdavi.sprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "messages_prefs";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.button.setOnClickListener(view -> {
            String message = binding.messageEditText.getText().toString().trim();
            Log.d("TAG", "onCreate: "+message);

            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("message", message);

            editor.apply(); //saving to disk

        });

        //Get data back from SP
        SharedPreferences getShareData = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        String value = getShareData.getString("message", "Nothing yet");
        binding.textView.setText(value);
    }
}