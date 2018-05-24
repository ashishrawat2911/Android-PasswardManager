package com.example.ashish.passwardmanager.data;

import android.provider.BaseColumns;


/**
 * Created by admin on 5/7/2018.
 */

public class PasswordContract {


    public static final class PasswordEntry implements BaseColumns {


        // Task table and column names
        public static final String TABLE_NAME = "password";
        public static final String COLUMN_ACCOUNT = "account";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";


    }
}
