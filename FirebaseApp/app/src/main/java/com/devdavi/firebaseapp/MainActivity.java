package com.devdavi.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devdavi.firebaseapp.databinding.ActivityMainBinding;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonUpload.setOnClickListener(view -> {

            //Configura para imagem ser salva em memória
            binding.imageFoto.setDrawingCacheEnabled(true);
            binding.imageFoto.buildDrawingCache();

            //Recupera bitmap da imagem (image a ser carregada)
            Bitmap bitmap = binding.imageFoto.getDrawingCache();

            //Comprimindo bitmap para um formato png/jpeg
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            //converte o outputStream para pixel brutos em uma matriz de bytes
            // (dados da imagem)
            byte[] dadosImagem = outputStream.toByteArray();


            /*
            //Define os nós para o storage
            StorageReference imagens = storageReference.child("imagens");
            StorageReference imagemRef = imagens.child("ae74c633-66c4-4c4d-b68f-70851a950b58");

            imagemRef.delete()
                    .addOnFailureListener(MainActivity.this, e -> {
                        Toast.makeText(getApplicationContext(), "Error ao deletar", Toast.LENGTH_LONG)
                                .show();
                    })
                    .addOnSuccessListener(MainActivity.this, unused -> {
                        Toast.makeText(getApplicationContext(), "Sucesso ao deletar", Toast.LENGTH_LONG)
                                .show();
                    });
             */

            //Define um nome único usando o padrão UUID
            StorageReference imagens = storageReference.child("imagens");
            String nomeArquivo = UUID.randomUUID().toString();
            StorageReference imagemRef = imagens.child("lente.jpeg");

            ImageView imageView = binding.imageFoto;

            Glide.with(MainActivity.this)
                    .load(imagemRef)
                    .into(imageView);

            /* 
            UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
            uploadTask
                    .addOnFailureListener(e -> {
                        Toast.makeText(getApplicationContext(), "Erro ao enviar imagem" + e.getMessage().toString(), Toast.LENGTH_LONG)
                                .show();
                    })
                    .addOnSuccessListener(taskSnapshot -> {
                        //Recebe a url gerada automaticamente pelo firebase
                        imagemRef.getDownloadUrl().addOnCompleteListener(task -> {
                            Uri url = task.getResult();
                            Toast.makeText(getApplicationContext(), "Imagem enviada com sucesso" + url.toString(), Toast.LENGTH_LONG)
                                    .show();
                        });
                    });
             */

        });

        /* Inicio da implementação do RealTime Database
        DatabaseReference usuarios = reference.child("usuarios");
        DatabaseReference usuarioPesquisa = usuarios.child("-MwN9xhRo3TDDiAq5WgI");

        //Filtrando por nome
        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Davi");

        //Consultando usuários ordenados pelo identificador e limitado por um tamanho
        Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);


        // Filtrando por nó e limitando por idade >= 12 <= 21
        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(12).endAt(21);


        //Pesquisa de um usuário especifico
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Usuario daddosUsuario = snapshot.getValue(Usuario.class);
//                Log.i("USER", "Nome: "+ daddosUsuario.getNome());
                Log.i("USER", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Criando identificado uníco com metódo push
        usuarios.push().setValue(new Usuario("Davi", "Ramos", 21));


        //Deslogando o usuário
        auth.signOut();

        //Login de usuário com email e senha

        auth.signInWithEmailAndPassword("exemmple@gmail.com", "ex000999666")
                .addOnCompleteListener(task -> {
                    //some code here
                });


        //Cadastrando um usuário

        auth.createUserWithEmailAndPassword("exemple@gmail.com", "ex000999666")

                .addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Usuário cadastrado", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário não cadastrado", Toast.LENGTH_LONG)
                                .show();
                    }
                }
        );



        // Salvando dados
        usuarios.child("001").setValue(new Usuario("Sandra", "Alcântara", 50));
        usuarios.child("003").setValue(new Usuario("Francisco", "Santana", 47));

        // Retrive de dados

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */
    }
}