package com.example.livemeeting;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JOINMEETING extends AppCompatActivity {

    private EditText meetingid, meetingname,meetingpassword;
    private Button join;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinmeeting);

        meetingid = findViewById(R.id.m1);
        meetingname= findViewById(R.id.n1);
        meetingpassword= findViewById(R.id.p1);
        join= findViewById(R.id.j1);

        db = openOrCreateDatabase("MeetingDB", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Meetings(MeetingID VARCHAR, MeetingName VARCHAR, MeetingPassword VARCHAR)");
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String meetingId = meetingid.getText().toString().trim();
                String meetingName = meetingname.getText().toString().trim();
                String meetingPassword = meetingpassword.getText().toString().trim();
//                joinMeeting();

                if (meetingId.isEmpty()) {
                    Toast.makeText(JOINMEETING.this, "Please enter a meetingId", Toast.LENGTH_SHORT).show();
                } else {
                    if (meetingName.isEmpty()) {
                        Toast.makeText(JOINMEETING.this, "Please enter a meetingName", Toast.LENGTH_SHORT).show();
                    }
                    if (meetingPassword.isEmpty()) {
                        Toast.makeText(JOINMEETING.this, "Please enter a meetingPassword", Toast.LENGTH_SHORT).show();
                    }

//                    Cursor cursor = db.rawQuery("SELECT * FROM users WHERE meetingID=? AND meetingName=? AND meetingPassword=?", new String[]{meetingId, meetingName, meetingPassword});
//                    if (cursor != null && cursor.getCount() > 0) {
//                        // Move to first row and fetch the user data
//                        cursor.moveToFirst();
//                        @SuppressLint("Range") String storedMeetingID = cursor.getString(cursor.getColumnIndex("meetingId"));
//                        @SuppressLint("Range") String storedMeetingName = cursor.getString(cursor.getColumnIndex("meetingName"));
//                        @SuppressLint("Range") String storedMeetingPassword = cursor.getString(cursor.getColumnIndex("meetingPassword"));
//                        cursor.close();
//
////                        if (username.equals(storedUsername) && password.equals(storedPassword)) {
////                            // Successful login, proceed to the next activity (e.g., HomeActivity)
////                            Intent intent = new Intent(LOGIN.this, REGISTRATION.class);
////                            startActivity(intent);
////                            finish();
////                        } else {
////                            Toast.makeText(LOGIN.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
////                        }
//                    }
                    else {
                        // Insert user data into the database
                        ContentValues values = new ContentValues();
                        values.put("meetingId", meetingId);
                        values.put("meetingName", meetingName);
                        values.put("meetingPassword", meetingPassword);
                        db.insert("meeting", null, values);
                        Toast.makeText(JOINMEETING.this, "Join a meeting Successfully......", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

//    private void joinMeeting() {
//        String meetingId = m1.getText().toString().trim();
//        String meetingName = n1.getText().toString().trim();
//        String meetingPassword = p1.getText().toString().trim();

        // Implement meeting join logic here (e.g., using Zoom SDK, Google Meet API, etc.)
        // Depending on the SDK or API, invoke the join meeting functionality using the entered meeting ID and password
        // For Zoom SDK, refer to the Zoom SDK documentation for meeting joining instructions
        // For other services, utilize their provided methods to join a meeting
//    }
}
