package com.devdavi.listadetarefas.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tarefa_table")
public class Tarefa implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "tarefa")
    private String nomeTarefa;

    public Tarefa() {
    }

    public Tarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public Tarefa(Long id, String nomeTarefa) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
