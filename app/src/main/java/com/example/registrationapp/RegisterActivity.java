package com.example.registrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class RegisterActivity extends AppCompatActivity {

    Button btnSave;
    EditText etName, etAddress, etGender, etBirthdate, etContact, etEmail;

    public static int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etGender = findViewById(R.id.etGender);
        etBirthdate = findViewById(R.id.etBirthdate);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);

    }

    public void OnClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OnClickSave(View view) {
        if (user_id == 0) {
            try {
                User user = new User(0,
                        etName.getText().toString(),
                        etAddress.getText().toString(),
                        etGender.getText().toString(),
                        etBirthdate.getText().toString(),
                        etContact.getText().toString(),
                        etEmail.getText().toString());

                DBHandler db = new DBHandler(RegisterActivity.this);
                db.AddRecord(user);

                Toast.makeText(RegisterActivity.this, "Successful Registration", Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(RegisterActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}