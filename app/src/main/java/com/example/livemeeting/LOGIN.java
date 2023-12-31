package com.example.livemeeting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LOGIN extends AppCompatActivity {

    private EditText username, password;
    private Button login;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.u1);
        password = findViewById(R.id.p1);
        login = findViewById(R.id.b2);
//        registerButton = findViewById(R.id.registerButton);

        // Creating or opening the database
        db = openOrCreateDatabase("UserDB", MODE_PRIVATE, null);

        // Creating a table if it doesn't exist
        db.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR, password VARCHAR)");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();

//                if (user.isEmpty()) {
//                    Toast.makeText(LOGIN.this, "Please enter a username", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (pwd.isEmpty()) {
//                        Toast.makeText(LOGIN.this, "Please enter a password", Toast.LENGTH_SHORT).show();
//                    }
//                    Toast.makeText(LOGIN.this, "Login Successfully......", Toast.LENGTH_SHORT).show();
//                }


                if (user.isEmpty()) {
                    Toast.makeText(LOGIN.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.isEmpty()) {
                        Toast.makeText(LOGIN.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    }
                    // Check if the user exists in the database
                    Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{user, pwd});
                    if (cursor != null && cursor.getCount() > 0) {
                        // Move to first row and fetch the user data
                        cursor.moveToFirst();
                        @SuppressLint("Range") String storedUsername = cursor.getString(cursor.getColumnIndex("user"));
                        @SuppressLint("Range") String storedPassword = cursor.getString(cursor.getColumnIndex("pwd"));
                        cursor.close();

//                        if (username.equals(storedUsername) && password.equals(storedPassword)) {
//                            // Successful login, proceed to the next activity (e.g., HomeActivity)
//                            Intent intent = new Intent(LOGIN.this, REGISTRATION.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(LOGIN.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//                        }
                    }
                    Toast.makeText(LOGIN.this, "Login Successfully......", Toast.LENGTH_SHORT).show();
//                    else {
//                        Toast.makeText(LOGIN.this, "User not found", Toast.LENGTH_SHORT).show();
//                    }
//                    if (user.isEmpty()) {
//                        Toast.makeText(LOGIN.this, "Please enter a username", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        if (pwd.isEmpty()) {
//                            Toast.makeText(LOGIN.this, "Please enter a password", Toast.LENGTH_SHORT).show();
//                        }
//                        Toast.makeText(LOGIN.this, "Login Successfully......", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Redirect to registration activity
//                Intent intent = new Intent(LOGIN.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

//        @Override
//        protected void onDestroy(){
//            super.onDestroy();
//            // Close the database connection on activity destruction
//            if (db != null && db.isOpen()) {
//                db.close();
//            }
//        }
    }
}
