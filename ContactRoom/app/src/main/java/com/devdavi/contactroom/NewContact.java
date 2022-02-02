package com.devdavi.contactroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.devdavi.contactroom.model.Contact;
import com.devdavi.contactroom.model.ContactViewModel;
import com.google.android.material.snackbar.Snackbar;

public class NewContact extends AppCompatActivity {

    //Constants
    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";

    //    Widget's
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;
    private Button deleteButton;
    private Button updateButton;

    //Instance
    private ContactViewModel contactViewModel;

    //Variables
    private int contactId = 0;
    private Boolean isEdit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        enterName = findViewById(R.id.enter_name);
        enterOccupation = findViewById(R.id.enter_occupation);
        saveInfoButton = findViewById(R.id.save_button);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this
                .getApplication())
                .create(ContactViewModel.class);

        if (getIntent().hasExtra(MainActivity.CONTACT_ID)) {
            contactId = getIntent().getIntExtra(MainActivity.CONTACT_ID, 0);

            contactViewModel.get(contactId).observe(this, contact -> {
                if (contact != null) {
                    enterName.setText(contact.getName());
                    enterOccupation.setText(contact.getOccupation());
                }
            });
            isEdit = true;
        }

        saveInfoButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())) {
                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();

                replyIntent.putExtra(NAME_REPLY, name);
                replyIntent.putExtra(OCCUPATION_REPLY, occupation);
                setResult(RESULT_OK, replyIntent);

            } else {
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();
        });

        //Update button
        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(view -> {
            String name = enterName.getText().toString().trim();
            String occupation = enterOccupation.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(occupation)) {
                Snackbar.make(enterName, R.string.empty, Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                Contact contact = new Contact(name, occupation);
                contact.setId(contactId);
                ContactViewModel.update(contact);
                finish();
            }
        });

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(view -> {
            String name = enterName.getText().toString().trim();
            String occupation = enterOccupation.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(occupation)) {
                Snackbar.make(enterName, R.string.empty, Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                Contact contact = new Contact(name, occupation);
                contact.setId(contactId);
                ContactViewModel.delete(contact);
                finish();
            }
        });

        if (isEdit) {
            saveInfoButton.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

    }
}