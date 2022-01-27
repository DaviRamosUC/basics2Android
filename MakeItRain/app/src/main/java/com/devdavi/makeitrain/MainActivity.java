package com.devdavi.makeitrain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private TextView moneyValue;
    private TextView successMessage;
    private int moneyCounter = 0;

    private Button newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_layout);

        newButton = findViewById(R.id.button);
        newButton.setOnClickListener(view -> {
            Log.d("newBtn", "onCreate: Olá");
        });

        moneyValue = findViewById(R.id.moneyValue);
        successMessage = findViewById(R.id.textView4);
    }

    public void showMoney(View view) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyCounter += 1000;
        moneyValue.setText(String.valueOf(numberFormat.format(moneyCounter)));

        if (moneyCounter == 20000){
            moneyValue.setTextColor(getResources().getColor(R.color.white,getTheme()));
            successMessage.setText(R.string.success_text);
        }

    }

    public void showInfo(View view) {
//        Toast.makeText(MainActivity.this, R.string.app_info, Toast.LENGTH_SHORT).show();

        Snackbar.make(moneyValue, R.string.app_info, Snackbar.LENGTH_SHORT)
                .setAction("More", view1 -> {
                    Log.d("Snack", "showInfo: Snackbar More");
                })
                .show();
    }
}