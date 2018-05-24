package com.example.ashish.passwardmanager;

import android.content.ContentValues;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.passwardmanager.data.PasswordContract.PasswordEntry;
import com.example.ashish.passwardmanager.data.PasswordDbHelper;



public class InputPasswordActivity extends AppCompatActivity  {
    EditText username, password, accountName;
    String usernameText, passwordText,accountText;
    TextView save;
    private SQLiteDatabase mDb;
    private boolean show = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password);
        username = findViewById(R.id.username_edit_text);
        password = findViewById(R.id.password_edit_text);
        save = findViewById(R.id.save);
        accountName = findViewById(R.id.account_spinner);
        // Create a DB helper (this will create the DB if run for the first time)
        PasswordDbHelper dbHelper = new PasswordDbHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers
        mDb = dbHelper.getWritableDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameText = username.getText().toString().trim();
                passwordText = password.getText().toString().trim();
                accountText = accountName.getText().toString().trim();

                if (!usernameText.equals("") && !passwordText.equals("")&&!accountText.equals("")) {
                    addNewPassword(usernameText, passwordText, accountText);
                    finish();
                } else
                    Toast.makeText(InputPasswordActivity.this, "Fill Entries", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private long addNewPassword(String usernameText, String passwordText, String spinnerText) {
        ContentValues cv = new ContentValues();
        cv.put(PasswordEntry.COLUMN_USERNAME, usernameText);
        cv.put(PasswordEntry.COLUMN_PASSWORD, passwordText);
        cv.put(PasswordEntry.COLUMN_ACCOUNT, spinnerText);
        return mDb.insert(PasswordEntry.TABLE_NAME, null, cv);
    }


}
