package com.example.ashish.passwardmanager.data;

/**
 * Created by admin on 5/7/2018.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ashish.passwardmanager.data.PasswordContract.PasswordEntry;

public class PasswordDbHelper extends SQLiteOpenHelper {

        // The name of the database
        private static final String DATABASE_NAME = "tasksDb.db";

        // If you change the database schema, you must increment the database version
        private static final int VERSION = 1;


        // Constructor
        public PasswordDbHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create tasks table (careful to follow SQL formatting rules)
            final String CREATE_TABLE = "CREATE TABLE "  + PasswordEntry.TABLE_NAME + " (" +
                    PasswordEntry._ID                + " INTEGER PRIMARY KEY, " +
                    PasswordEntry.COLUMN_ACCOUNT + " TEXT NOT NULL, " +
                    PasswordEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
                    PasswordEntry.COLUMN_PASSWORD    + " TEXT NOT NULL);";

            db.execSQL(CREATE_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PasswordEntry.TABLE_NAME);
            onCreate(db);
        }
    }

