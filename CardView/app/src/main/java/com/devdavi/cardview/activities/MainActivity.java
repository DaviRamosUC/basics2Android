package com.devdavi.cardview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.devdavi.cardview.R;
import com.devdavi.cardview.adapter.RecyclerViewPostagem;
import com.devdavi.cardview.model.Post;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPostagem;
    private List<Post> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerPostagem = findViewById(R.id.recyclerPostagem);

        //Define adapter
        RecyclerViewPostagem adapter = new RecyclerViewPostagem(postagens);
        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerPostagem.setLayoutManager(layoutManager);
        recyclerPostagem.setHasFixedSize(true);

        recyclerPostagem.setAdapter(adapter);
        prepararPostagens();
    }

    public void prepararPostagens() {
        this.postagens.add(new Post("Nilce Moreto", "Curtindo a vista com doguinho", R.drawable.nilcemock));
        this.postagens.add(new Post("Leon Martins", "Curtindo o setup novo", R.drawable.leonmock));
        this.postagens.add(new Post("Davi Ramos", "Curtindo morar aqui", R.drawable.lisboa));

    }
}