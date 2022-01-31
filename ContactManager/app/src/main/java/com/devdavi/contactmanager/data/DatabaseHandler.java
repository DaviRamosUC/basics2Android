package com.devdavi.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devdavi.contactmanager.R;
import com.devdavi.contactmanager.model.Contact;
import com.devdavi.contactmanager.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    // We create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT," +
                Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);

    }

//    CRUD = CREATE, READ, UPDATE, DELETE

    //Add Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        // Insert to row
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
    }

    //Get a contact by id
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + " =?", new String[]{String.valueOf(id)},
                null,
                null,
                null);

        Contact contact = null;

        if (cursor != null) {
            cursor.moveToFirst();
            contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        }

        return contact;
    }
}
