package com.example.livemeeting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class DBhelper extends SQLiteOpenHelper {


    public static final String DBNAME="Login.db";
//    public static final String DBNAMER="Registration.db";
    @RequiresApi(api = Build.VERSION_CODES.P)
    public DBhelper(Context context){
        super(context ,"Login.db", 1,null);
    }


//    @RequiresApi(api = Build.VERSION_CODES.P)
//    public void DBhelper1(Context context){
//        super(context ,"Registration.db", 1,null);
//    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table username(username TEXT primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
    }


    public Boolean insertData(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username = ?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
class MeetingDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "meetings_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MEETINGS = "meetings";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MEETING_ID = "meeting_id";
    private static final String COLUMN_MEETING_NAME = "meeting_name";
    private static final String COLUMN_MEETING_PASSWORD = "meeting_password";

    public MeetingDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEETINGS_TABLE = "CREATE TABLE " + TABLE_MEETINGS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_MEETING_ID + " TEXT," +
                COLUMN_MEETING_NAME + " TEXT," +
                COLUMN_MEETING_PASSWORD + " TEXT" +
                ")";
        db.execSQL(CREATE_MEETINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEETINGS);
        onCreate(db);
    }

    public void addMeeting(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEETING_ID, meeting.getMeetingId());
        values.put(COLUMN_MEETING_NAME, meeting.getMeetingName());
        values.put(COLUMN_MEETING_PASSWORD, meeting.getMeetingPaaword());
        // Other meeting attributes...

        db.insert(TABLE_MEETINGS, null, values);
        db.close();
    }

    // Methods to retrieve, update, delete meetings, etc.
}




