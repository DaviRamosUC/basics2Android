package com.devdavi.contactmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.devdavi.contactmanager.data.DatabaseHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        contactArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);


//        db.addContact(new Contact("James","213986"));
//        db.addContact(new Contact("Greg","098765"));
//        db.addContact(new Contact("Helena","40678765"));
//        db.addContact(new Contact("Casemiro","768345"));
//        db.addContact(new Contact("Davi","152121"));
//        db.addContact(new Contact("Luana","2665254"));

        db.getAllContacts().forEach(contact -> {
            Log.d("TAG", "onCreate: " + contact.getName());
            contactArrayList.add(contact.getName());
        });

        // create array adapter

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );

        // add to our listview
        listView.setAdapter(arrayAdapter);

        //Attach eventlistener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d("LIST", "onItemClick: "+position);
            }
        });

    }
}