package com.devdavi.listadetarefas.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.devdavi.listadetarefas.models.Tarefa;
import com.devdavi.listadetarefas.util.TarefaRoomDatabase;

import java.util.List;

public class TarefaRepository implements TarefaDao {

    private TarefaDao tarefaDao;
    private LiveData<List<Tarefa>> contatos;

    public TarefaRepository(Application application) {
        TarefaRoomDatabase db = TarefaRoomDatabase.getDatabase(application);
        tarefaDao = db.tarefaDao();

        contatos = tarefaDao.getAlltarefas();
    }

    @Override
    public void insert(Tarefa tarefa) {
        TarefaRoomDatabase.databaseWriterExecutor.execute(() -> tarefaDao.insert(tarefa));
    }

    @Override
    public LiveData<List<Tarefa>> getAlltarefas() {
        return contatos;
    }

    @Override
    public LiveData<Tarefa> get(Long id) {
        return tarefaDao.get(id);
    }

    @Override
    public void update(Tarefa tarefa) {
        TarefaRoomDatabase.databaseWriterExecutor.execute(() -> tarefaDao.update(tarefa));
    }

    @Override
    public void deleteAll() {
        tarefaDao.deleteAll();
    }

    @Override
    public void delete(Tarefa tarefa) {
        TarefaRoomDatabase.databaseWriterExecutor.execute(() -> tarefaDao.delete(tarefa));
    }
}
