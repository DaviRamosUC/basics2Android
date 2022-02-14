package com.devdavi.fragments.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.devdavi.fragments.R;
import com.devdavi.fragments.fragment.ContatosFragment;
import com.devdavi.fragments.fragment.ConversasFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button buttonConversas, buttonContatos;
    private ConversasFragment conversasFragment;
    private ContatosFragment contatosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConversas = findViewById(R.id.buttonConversas);
        buttonContatos = findViewById(R.id.buttonContatos);

        // Remove o elavation do Actionbar
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        conversasFragment = new ConversasFragment();
        contatosFragment = new ContatosFragment();

        fragmnetTransaction(R.id.frameConteudo, conversasFragment);

        buttonContatos.setOnClickListener(view -> {
            fragmnetTransaction(R.id.frameConteudo, contatosFragment);
        });

        buttonConversas.setOnClickListener(view -> {
            fragmnetTransaction(R.id.frameConteudo, conversasFragment);
        });

    }

    private void fragmnetTransaction(int viewId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewId, fragment);
        transaction.commit();
    }
}