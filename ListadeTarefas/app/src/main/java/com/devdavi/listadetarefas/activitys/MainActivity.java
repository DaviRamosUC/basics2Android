package com.devdavi.listadetarefas.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devdavi.listadetarefas.adapters.TarefaRecyclerViewAdapter;
import com.devdavi.listadetarefas.databinding.ActivityMainBinding;
import com.devdavi.listadetarefas.models.Tarefa;
import com.devdavi.listadetarefas.models.TarefaViewModel;
import com.devdavi.listadetarefas.util.RecyclerItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private TarefaViewModel viewModel;
    private List<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        recyclerView = binding.contentMain.recyclerView;

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(TarefaViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                                intent.putExtra("tarefa", tarefas.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Tarefa tarefaSelecionada = tarefas.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir a terefa: " + tarefaSelecionada.getNomeTarefa() + "?");
                                dialog.setPositiveButton("Confirmar", (dialogInterface, i) -> {
                                    TarefaViewModel.delete(tarefaSelecionada);
                                }).setNegativeButton("Não", null);

                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

        viewModel.getAllTarefas().observe(this, tarefas -> {
            this.tarefas = tarefas;
            TarefaRecyclerViewAdapter adapter = new TarefaRecyclerViewAdapter(tarefas);
            recyclerView.setAdapter(adapter);

        });

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
            startActivity(intent);
        });
    }


}