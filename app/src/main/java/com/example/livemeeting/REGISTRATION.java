package com.example.livemeeting;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class REGISTRATION extends AppCompatActivity {

    private EditText username, password,email,confirmpassword,address,mobilenumber;
    private Button registration;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.e1);
        password = findViewById(R.id.e2);
        email = findViewById(R.id.e3);
        confirmpassword=findViewById(R.id.e5);
        address=findViewById(R.id.e4);
        mobilenumber=findViewById(R.id.e6);
        registration = findViewById(R.id.b1);

        // Creating or opening the database
        db = openOrCreateDatabase("UserDB", MODE_PRIVATE, null);

        // Creating a table if it doesn't exist
        db.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR, password VARCHAR, email VARCHAR, confirmpwd VARCHAR, address VARCHAR, MobileNumber VARCHAR)");

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String cpwd= confirmpassword.getText().toString().trim();
                String add= address.getText().toString().trim();
                String mno= mobilenumber.getText().toString().trim();

                if (username.equals("") || password.equals("") || email.equals("")) {
                    Toast.makeText(REGISTRATION.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
               }
                if (user.isEmpty()) {
                    Toast.makeText(REGISTRATION.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.isEmpty()) {
                        Toast.makeText(REGISTRATION.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    }
                    if (mail.isEmpty()) {
                        Toast.makeText(REGISTRATION.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    }
                    if (cpwd.isEmpty()) {
                        Toast.makeText(REGISTRATION.this, "Please enter a confirm password", Toast.LENGTH_SHORT).show();
                    }
                    if (add.isEmpty()) {
                        Toast.makeText(REGISTRATION.this, "Please enter a address", Toast.LENGTH_SHORT).show();
                    }
                    if (mno.isEmpty()) {
                        Toast.makeText(REGISTRATION.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    }
//                if (username.equals("") || password.equals("") || email.equals("")) {
//                    Toast.makeText(REGISTRATION.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                }
                    else {
                        // Insert user data into the database
                        ContentValues values = new ContentValues();
                        values.put("username", user);
                        values.put("password", pwd);
                        values.put("email", mail);
                        values.put("cpwd", mail);
                        values.put("add", mail);
                        values.put("mno", mail);
                        db.insert("users", null, values);

                        Toast.makeText(REGISTRATION.this, "Registration successful", Toast.LENGTH_SHORT).show();

                        // Optionally, you can navigate to another activity after successful registration
                        // For example, redirect to LoginActivity
                        // Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        // startActivity(intent);
                        finish(); // Finish the current activity
                    }

                }
            }
        });
    }
}
