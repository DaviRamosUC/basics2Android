package com.devdavi.listadetarefas.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.devdavi.listadetarefas.data.TarefaRepository;

import java.util.List;

public class TarefaViewModel extends AndroidViewModel {

    public static TarefaRepository repository;
    public final LiveData<List<Tarefa>> tarefas;

    public TarefaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new TarefaRepository(application);
        this.tarefas = repository.getAlltarefas();
    }

    public LiveData<List<Tarefa>> getAllTarefas() {
        return tarefas;
    }

    public static void insert(Tarefa tarefa) {
        repository.insert(tarefa);
    }

    public LiveData<Tarefa> getTarefa(int id) {
        return repository.get((long) id);
    }

    public static void update(Tarefa tarefa) {
        repository.update(tarefa);
    }

    public static void delete(Tarefa tarefa) {
        repository.delete(tarefa);
    }
}
