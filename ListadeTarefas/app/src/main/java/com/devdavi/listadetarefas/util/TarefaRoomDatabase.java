package com.devdavi.listadetarefas.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.devdavi.listadetarefas.data.TarefaDao;
import com.devdavi.listadetarefas.models.Tarefa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tarefa.class}, version = 1, exportSchema = false)
public abstract class TarefaRoomDatabase extends RoomDatabase {

    public static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static volatile TarefaRoomDatabase INSTANCE;

    public static TarefaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TarefaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TarefaRoomDatabase.class, "contact_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TarefaDao tarefaDao();

}
