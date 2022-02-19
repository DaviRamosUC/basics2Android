package com.devdavi.listadetarefas.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devdavi.listadetarefas.R;
import com.devdavi.listadetarefas.databinding.ActivityAdicionarTarefaBinding;
import com.devdavi.listadetarefas.models.Tarefa;
import com.devdavi.listadetarefas.models.TarefaViewModel;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private ActivityAdicionarTarefaBinding binding;
    private TarefaViewModel tarefaViewModel;
    private Tarefa tarefaAtual;
    private TarefaViewModel viewModel;
    private String tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarTarefaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(TarefaViewModel.class);

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefa");
        tarefa = tarefaAtual.getNomeTarefa();

        if (tarefaAtual != null) {
            binding.inputTarefa.setText(tarefaAtual.getNomeTarefa());
        }

        tarefaViewModel = new ViewModelProvider.AndroidViewModelFactory(
                AdicionarTarefaActivity.this.getApplication()
        ).create(TarefaViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_confirmar) {
            if (tarefaAtual != null) {


                if (binding.inputTarefa.getText().toString().equals(tarefa)) {
                    Toast.makeText(getApplicationContext(), "Para salvar a edição primeiro modifique a tarefa", Toast.LENGTH_LONG)
                            .show();
                } else {
                    tarefaAtual.setNomeTarefa(binding.inputTarefa.getText().toString());
                    TarefaViewModel.update(tarefaAtual);
                    Toast.makeText(getApplicationContext(), "Sucesso ao editar tarefa", Toast.LENGTH_LONG)
                            .show();
                    finish();
                }
            } else {
                String novaTarefa = binding.inputTarefa.getText().toString();
                TarefaViewModel.insert(new Tarefa(novaTarefa));
                Toast.makeText(getApplicationContext(), "Tarefa criada", Toast.LENGTH_LONG)
                        .show();
                finish();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}