package com.devdavi.listadetarefas.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.devdavi.listadetarefas.models.Tarefa;

import java.util.List;

@Dao
public interface TarefaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tarefa tarefa);

    @Query("SELECT * FROM tarefa_table ORDER BY tarefa ASC")
    LiveData<List<Tarefa>> getAlltarefas();

    @Query("SELECT * FROM tarefa_table WHERE tarefa_table.id == :id")
    LiveData<Tarefa> get(Long id);

    @Update
    void update(Tarefa tarefa);

    @Query("DELETE FROM tarefa_table")
    void deleteAll();

    @Delete
    void delete(Tarefa tarefa);

}
