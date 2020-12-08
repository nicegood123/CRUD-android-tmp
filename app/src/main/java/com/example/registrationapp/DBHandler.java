package com.example.registrationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbUser.db";
    public static final String TBL_USER = "tblUser";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_ADDRESS = "address";
    public static final String COL_GENDER = "gender";
    public static final String COL_BIRTHDATE = "birthdate";
    public static final String COL_CONTACT = "contact";
    public static final String COL_EMAIL = "email";



    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    //creates table
    public void onCreate(SQLiteDatabase db) {
        try {
            String strQuery = "CREATE TABLE " + TBL_USER + "(" + COL_ID + " INTEGER Primary Key AUTOINCREMENT,"
                    + COL_NAME + " TEXT, "
                    + COL_ADDRESS + " TEXT, "
                    + COL_GENDER + " TEXT, "
                    + COL_BIRTHDATE + " TEXT, "
                    + COL_CONTACT + " TEXT, "
                    + COL_EMAIL + " TEXT)";
            db.execSQL(strQuery);
        }catch (Exception ex){
            Toast.makeText(null,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TBL_USER);
        onCreate(db);
    }


    public boolean AddRecord(User user){
        //Add Record to the database using content values. Content values has two parameters(COLUMN NAME, Data to be Stored)
        //AddRecord(Student student) we just invoke/called the Student class so that we will be able to call and used the setters and getters
        //which is very useful on this part because we will be getting all the users input from our UI.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME,user.getName());
        cv.put(COL_ADDRESS,user.getAddress());
        cv.put(COL_GENDER,user.getGender());
        cv.put(COL_BIRTHDATE,user.getBirthdate());
        cv.put(COL_CONTACT,user.getContact());
        cv.put(COL_EMAIL,user.getEmail());

        long insert = db.insert(TBL_USER, null, cv);

        if (insert==-1) return false;
        else return true;
    }



    public List<User> viewAllData(){
        List<User> returnAllData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM " + TBL_USER, null);

        if(cr.getCount()>0){
            while(cr.moveToNext()){
                int id = cr.getInt(0);
                String name = cr.getString(1);
                String address = cr.getString(2);
                String gender = cr.getString(3);
                String birthdate = cr.getString(4);
                String contact = cr.getString(5);
                String email = cr.getString(6);


                User allUser = new User(id, name, address, gender, birthdate, contact, email);
                returnAllData.add(allUser);
            }
        }
        else{

        }
        cr.close();
        db.close();
        return returnAllData;
    }


    public boolean DeleteRecord(User user){

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor strQuery = db.rawQuery("DELETE FROM " + TBL_USER + " WHERE " + COL_ID + " = " + user.getUserID(),null);

        if(strQuery.moveToFirst()) return true;
        else return false;
    }

    public boolean UpdateRecord(User user){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_ID,user.getUserID());
        cv.put(COL_NAME,user.getName());
        cv.put(COL_ADDRESS,user.getAddress());
        cv.put(COL_GENDER,user.getGender());
        cv.put(COL_BIRTHDATE,user.getBirthdate());
        cv.put(COL_CONTACT,user.getContact());
        cv.put(COL_EMAIL,user.getEmail());

        db.update(TBL_USER,cv,"ID=?", new String[]{String.valueOf(user.getUserID())});
        return true;
    }



}

