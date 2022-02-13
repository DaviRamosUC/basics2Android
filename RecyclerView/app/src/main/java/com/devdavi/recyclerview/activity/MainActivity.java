package com.devdavi.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.devdavi.recyclerview.R;
import com.devdavi.recyclerview.RecyclerItemClickListener;
import com.devdavi.recyclerview.adapter.RecyclerViewAdapter;
import com.devdavi.recyclerview.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        // Listagem de filmes
        this.criarFilmes();

        //Configurando adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listaFilmes);

        //Configurando recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        //evento de click
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Filme filme = listaFilmes.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item pressionado: " + filme.getTituloFilme(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Filme filme = listaFilmes.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Click longo: "+ filme.getTituloFilme(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        })
        );

    }

    public void criarFilmes() {
        this.listaFilmes.add(new Filme("The Seven Deadly Sins: Cursed by Light", "Anime", "2021"));
        this.listaFilmes.add(new Filme("A era do gelo", "Infantil", "2022"));
        this.listaFilmes.add(new Filme("Hotel Transilvânia", "Infantil", "2022"));
        this.listaFilmes.add(new Filme("The flash", "Ação", "2014"));
        this.listaFilmes.add(new Filme("Megalon", "Ação", "2018"));
        this.listaFilmes.add(new Filme("O imbatível 3", "Ação", "2010"));
        this.listaFilmes.add(new Filme("Tokyo Ghoul", "Anime", "2017"));
        this.listaFilmes.add(new Filme("King's Man", "Fantasia", "2021"));
        this.listaFilmes.add(new Filme("Kimi", "Ação", "2022"));
        this.listaFilmes.add(new Filme("Euphoria", "Juvenil", "2019"));
        this.listaFilmes.add(new Filme("Pacificador", "Fantasia", "2019"));
        this.listaFilmes.add(new Filme("Gray's anatomy", "Novel", "2005"));
    }
}