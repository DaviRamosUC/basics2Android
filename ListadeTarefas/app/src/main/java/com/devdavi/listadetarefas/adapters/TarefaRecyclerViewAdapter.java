package com.devdavi.listadetarefas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devdavi.listadetarefas.R;
import com.devdavi.listadetarefas.data.TarefaRepository;
import com.devdavi.listadetarefas.models.Tarefa;

import java.util.List;

public class TarefaRecyclerViewAdapter extends RecyclerView.Adapter<TarefaRecyclerViewAdapter.ViewHolder> {

    private List<Tarefa> tarefaList;

    public TarefaRecyclerViewAdapter(List<Tarefa> tarefaList) {
        this.tarefaList = tarefaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarefa_layout_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tarefa.setText(tarefaList.get(position).getNomeTarefa());
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tarefa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textView);
        }
    }
}
