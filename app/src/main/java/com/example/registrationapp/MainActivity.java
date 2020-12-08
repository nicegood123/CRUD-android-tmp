package com.example.registrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHandler db;

    ListView lvUser;
    SearchView svSearch;
    ArrayAdapter userArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);
        lvUser = findViewById(R.id.lvUser);
        svSearch = findViewById(R.id.svSearch);

        try {
            List<User> users_list = db.viewAllData();
            userArray = new ArrayAdapter<User>(MainActivity.this, android.R.layout.simple_list_item_1, users_list);
            lvUser.setAdapter(userArray);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            User user;
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = (User) parent.getItemAtPosition(position);

                AlertDialog.Builder ca= new AlertDialog.Builder(MainActivity.this);
                ca.setPositiveButton("EDIT", (dialog, which) -> {
                    Intent i = new Intent(MainActivity.this, EditActivity.class);
                    String value = "" + user.getUserID();
                    i.putExtra("value", value);
                    startActivity(i);
                })
                        .setNegativeButton("DELETE", (dialog, which) -> {
                            db.DeleteRecord(user);
                            Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_LONG).show();
                        });
                AlertDialog alert = ca.create();
                alert.setTitle("Select:");
                alert.show();
            }
        });

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    userArray.getFilter().filter(newText);
                    return false;
                }catch (Exception e){
                    DBHandler db = new DBHandler(MainActivity.this);
                    List<User> users_list = db.viewAllData();

                    userArray = new ArrayAdapter<User>(MainActivity.this,android.R.layout.simple_list_item_1, users_list);
                    lvUser.setAdapter(userArray);
                    return false;
                }
            }
        });
    }

    public void OnClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}