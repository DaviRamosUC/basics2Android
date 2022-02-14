package com.devdavi.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {

    private ImageButton voltar;
    private ImageView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        resultado = findViewById(R.id.imageViewResultado);

        Bundle data = getIntent().getExtras();

        int numero = data.getInt("numero");

        if (numero == 1) {
            resultado.setImageResource(R.drawable.moeda_coroa);
        }

        voltar = findViewById(R.id.imageButtonVoltar);
        voltar.setOnClickListener(view -> {
            finish();
        });
    }
}