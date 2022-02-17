package com.devdavi.sheredpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.devdavi.sheredpreferences.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static final String NAME_ID = "NAME_ID";
    private ActivityMainBinding binding;

    private TextView resultado;
    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        resultado = binding.resultado;
        editText = binding.nomeEditiText;

        existsName();

        binding.button.setOnClickListener(view1 -> {
            SharedPreferences sharedPreferences = getSharedPreferences(NAME_ID, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String nome = editText.getText().toString();
            if (nome.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Primeiro digite seu nome",
                        Toast.LENGTH_LONG
                )
                        .show();
            } else {
                editor.putString("nome", nome);
                editor.apply();
                inputName(nome);
            }
        });


    }

    private void existsName() {
        SharedPreferences getShareData = getSharedPreferences(NAME_ID, MODE_PRIVATE);
        String nome = getShareData.getString("nome", "");

        if (!nome.equals("")) {
            inputName(nome);
        }
    }

    private void inputName(String nome) {
        resultado.setText(String.format("Bem vindo %s!", nome));
        resultado.setVisibility(View.VISIBLE);
    }
}