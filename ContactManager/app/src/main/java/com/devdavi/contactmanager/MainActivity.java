package com.devdavi.contactmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.devdavi.contactmanager.data.DatabaseHandler;
import com.devdavi.contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        //Create and save contact
        Contact contact = new Contact("Davi", "71991360431");
//        db.addContact(contact);

        //Update contact
        Contact contact2 = db.getContact(2);
        contact2.setName("Sandra");
        contact2.setPhoneNumber("71992022483");
        int uptaded = db.updateContact(contact2);

        //Get all contact
        List<Contact> contactList = db.getAllContacts();
        for (Contact c : contactList) {
            Log.d("MainAC", "onCreate: " + c.getName() + " " + c.getPhoneNumber() + " " + c.getId());
        }

        // Delete a contact
//        Contact contact3 = db.getContact(3);
//        db.deleteContent(contact3);

        int count = db.countContact();
        Log.d("Count", "onCreate: "+ count);
    }
}