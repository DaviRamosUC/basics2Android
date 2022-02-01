package com.devdavi.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.devdavi.contactroom.model.Contact;
import com.devdavi.contactroom.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>>allContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();

        allContacts = contactDao.getAllContact();

    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public void insert(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(()->{
            contactDao.insert(contact);
        });
    }
}
