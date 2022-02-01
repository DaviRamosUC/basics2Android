package com.devdavi.contactroom;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devdavi.contactroom.model.Contact;
import com.devdavi.contactroom.model.ContactViewModel;

public class MainActivity extends AppCompatActivity {
    private ContactViewModel contactViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, contacts -> {
            StringBuilder builder = new StringBuilder();
            for (Contact c : contacts) {
                builder.append(" - ").append(c.getName()).append(" ").append(c.getOccupation());
                Log.d("Main", "onCreate: " + c.getName());
            }
            textView.setText(builder.toString());
        });
    }
}