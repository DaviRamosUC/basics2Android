package com.devdavi.minhasanotacoes;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.devdavi.minhasanotacoes.utils.AnotacaoPreferencias;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.devdavi.minhasanotacoes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AnotacaoPreferencias preferences;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = new AnotacaoPreferencias(this);

        recuperaAnotacao();

        binding.fab.setOnClickListener(view -> {
            String textoRecuperado = binding.contentMain.editAnotacao.getText().toString();
            if (!textoRecuperado.isEmpty()) {
                preferences.salvarAnotacao(textoRecuperado);
                Snackbar.make(view, "Anotação salva com sucesso", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(view, "Preencha a anotação", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void recuperaAnotacao() {
        String anotacaoSalva = preferences.recuperarAnotacao();
        Log.d("TAG", "recuperaAnotacao: "+ anotacaoSalva);
        if (!anotacaoSalva.isEmpty()) {
            binding.contentMain.editAnotacao.setText(anotacaoSalva);
        }
    }

}