package com.devdavi.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageButton jogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jogar = findViewById(R.id.imageButtonJogar);

        jogar.setOnClickListener(view -> {
            Intent intent = new Intent(this, ResultadoActivity.class);
            int numero = gerarValor();
            intent.putExtra("numero", numero);
            startActivity(intent);
        });

    }

    public int gerarValor() {
        return new Random().nextInt(2);
    }
}