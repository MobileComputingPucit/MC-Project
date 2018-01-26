package com.example.speedy.pucitdossier;

/**
 * Created by Speedy on 16/01/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "Dossier";

    // Contacts table name
    public static final String TABLE_NAME = "Signup";

    // Contacts Table Columns names
    private static final String KEY_ACCOUNT_ID = "Account_id";
    public static final String KEY_EMAIL = "Email";
    private static final String KEY_USERNAME = "Username";
    public static final String KEY_PASSWORD = "Password";
    private static final String KEY_PHONE_NO = "Phone_no";
    private static final String KEY_GENDER = "Gender";
    private static final String KEY_DATE_OF_BIRTH = "Date_of_birth";
    private static final String KEY_ENTRY_DATE = "Entry_date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ACCOUNT_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT," + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_PHONE_NO + " TEXT," +
                KEY_GENDER + " TEXT," + KEY_DATE_OF_BIRTH + " TEXT," +
                KEY_ENTRY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("Message", "Creating...");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
    //All CRUD(Create, Read, Update, Delete) Operations

    // Adding new contact
    long addUser(Signup signup) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, signup.getEmail()); // User Name
        values.put(KEY_USERNAME , signup.getUsername());
        values.put(KEY_PASSWORD, signup.getPassword());
        values.put(KEY_PHONE_NO, signup.getPhone_no()); // User Phone Number
        values.put(KEY_GENDER, signup.getGender());
        values.put(KEY_DATE_OF_BIRTH, signup.getDate_of_Birth());
        values.put(KEY_ENTRY_DATE, signup.getEntry_date());

        // Inserting Row
        long row = db.insert(TABLE_NAME, null, values);
        Log.d("Message", "Inserting...");
  //      db.close(); // Closing database connection
        return  row;
    }

    // Deleting single contact
    public void deleteContact(Signup signup) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ACCOUNT_ID + " = ?", new String[] { String.valueOf(signup.getAccount_id()) });
        db.close();
    }


}

