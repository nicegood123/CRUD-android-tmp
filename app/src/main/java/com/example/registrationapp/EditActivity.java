package com.example.registrationapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    DBHandler db;

    Button btnSaveChanges;
    EditText etName, etAddress, etGender, etBirthdate, etContact, etEmail;


    public static int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etGender = findViewById(R.id.etGender);
        etBirthdate = findViewById(R.id.etBirthdate);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);

        db = new DBHandler(this);

        boolean isActive;
        Bundle values = getIntent().getExtras();
        if(values == null) { return; }
        String value = values.getString("value");
        int get_id = Integer.parseInt(value);

        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("dbUser.db", Context.MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM tblUser WHERE id=" + get_id, null);
        while (cursor.moveToNext()) {
            int index2 = cursor.getColumnIndex("name");
            int index3 = cursor.getColumnIndex("address");
            int index4 = cursor.getColumnIndex("gender");
            int index5 = cursor.getColumnIndex("birthdate");
            int index6 = cursor.getColumnIndex("contact");
            int index7 = cursor.getColumnIndex("email");

            String name = cursor.getString(index2);
            String address = cursor.getString(index3);
            String gender = cursor.getString(index4);
            String birthdate = cursor.getString(index5);
            String contact = cursor.getString(index6);
            String email = cursor.getString(index7);

            etName.setText(name);
            etAddress.setText(address);
            etGender.setText(gender);
            etBirthdate.setText(birthdate);
            etContact.setText(contact);
            etEmail.setText(email);

        }
        user_id = get_id;
    }

    public void OnClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void  OnClickSaveChanges(View view) {
        try {
            User user = new User(user_id,
                    etName.getText().toString(),
                    etAddress.getText().toString(),
                    etGender.getText().toString(),
                    etBirthdate.getText().toString(),
                    etContact.getText().toString(),
                    etEmail.getText().toString());
            db.UpdateRecord(user);
            Toast.makeText(getApplicationContext(),"Data Successfully Updated",Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error: "+ e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}