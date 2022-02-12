package com.devdavi.calculadoradegorjeta;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText valor;
    private SeekBar seekBar;
    private TextView porcentagem;
    private TextView gorjeta;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor = findViewById(R.id.valor);
        seekBar = findViewById(R.id.seekBar);
        porcentagem = findViewById(R.id.porcentagem);
        gorjeta = findViewById(R.id.gorjeta);
        total = findViewById(R.id.total);
        calcularGorjeta();
    }

    public void calcularGorjeta() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (!Objects.requireNonNull(valor.getText()).toString().isEmpty()) {
                    seekBar.setMax(Integer.parseInt(valor.getText().toString()));
                    double valorDigitado = Double.parseDouble(valor.getText().toString());
                    porcentagem.setText(String.format("%d%%", progress));
                    double valorGorjeta = valorDigitado * progress / 100;
                    gorjeta.setText(String.format("R$ %s", formatador(valorGorjeta)));
                    double valorTotal = valorDigitado + valorGorjeta;
                    total.setText(String.format("R$ %s", formatador(valorTotal)));
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Primeiro nos informe o valor");
                    dialog.setMessage("Exemplo de valor: 100.00");
                    dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                        seekBar.setProgress(0);
                        valor.setText("0");
                    });
                    dialog.setCancelable(false);
                    dialog.create();
                    dialog.show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public String formatador(Double valor) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(valor);
    }
}