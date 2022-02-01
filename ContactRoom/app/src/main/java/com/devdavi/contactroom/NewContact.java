package com.devdavi.contactroom;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devdavi.contactroom.model.Contact;
import com.devdavi.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {
    //    Widget's
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        enterName = findViewById(R.id.enter_name);
        enterOccupation = findViewById(R.id.enter_occupation);
        saveInfoButton = findViewById(R.id.save_button);

        saveInfoButton.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())) {
                Contact contact = new Contact(enterName.getText().toString(), enterOccupation.getText().toString());
                ContactViewModel.insert(contact);
            } else {
                Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT)
                        .show();
            }


        });
    }
}