package com.devdavi.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase bd = openOrCreateDatabase("app", MODE_PRIVATE, null);

            // Criando a tabela
            bd.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

//            bd.execSQL("DROP TABLE pessoas");
//            bd.execSQL("UPDATE pessoas SET idade = 19 WHERE nome = 'Mariana'");

            // Inserindo dados
//            bd.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Maria', 35)");
//            bd.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Mariana', 18)");

//            bd.execSQL("DELETE FROM pessoas WHERE id = 1");


            //Recuperar pessoas
            String consulta = "SELECT * FROM pessoas WHERE id = 1";

            Cursor cursor = bd.rawQuery(consulta, null);

            //Indices da tabela
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(indiceId);
                    String nome = cursor.getString(indiceNome);
                    int idade = cursor.getInt(indiceIdade);
                    Log.i("RESPONSE", "ID: " + id + " " + nome + " / idade " + idade);
                } while (cursor.moveToNext());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}