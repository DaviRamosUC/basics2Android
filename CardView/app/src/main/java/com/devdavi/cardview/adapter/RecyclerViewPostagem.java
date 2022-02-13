package com.devdavi.cardview.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devdavi.cardview.R;
import com.devdavi.cardview.model.Post;

import java.util.List;

public class RecyclerViewPostagem extends RecyclerView.Adapter<RecyclerViewPostagem.ViewHolder> {
    private List<Post> postsList;

    public RecyclerViewPostagem(List<Post> postagens) {
        postsList = postagens;
        Log.d("TAG", "getItemCount: " + postagens.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.postagem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post p = postsList.get(position);
        holder.username.setText(p.getUsername());
        holder.comentario.setText(p.getPostagem());
        holder.imagem.setImageResource(p.getImagem());
        holder.like.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "O post foi curtido", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView comentario;
        public ImageView imagem;
        public Button like;
        public Button comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            imagem = itemView.findViewById(R.id.imageView);
            comentario = itemView.findViewById(R.id.comentario);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
