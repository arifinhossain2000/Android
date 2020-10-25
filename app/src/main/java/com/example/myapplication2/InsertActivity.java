package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText codeEdittext, typeEdittext, nameEdittext;
    private Button insert, list;
    private Mydatabse mydatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mydatabse = new Mydatabse(InsertActivity.this);
        SQLiteDatabase sqLiteDatabase = mydatabse.getWritableDatabase();

        codeEdittext= findViewById(R.id.insertCodeId);
        typeEdittext=findViewById(R.id.insertTypeId);
        nameEdittext=findViewById(R.id.insertNameId);
        insert=findViewById(R.id.insertId);
        list=findViewById(R.id.ViewId);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = nameEdittext.getText().toString();
                String Type = typeEdittext.getText().toString();
                String Code = codeEdittext.getText().toString();
                insertData(Name,Type,Code);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InsertActivity.this,SeventhActivity.class);
                startActivity(intent);

            }
        });

    }

    private void insertData(String name, String type, String code) {
        long value=mydatabse.insert(name,type,code);

        if(value == -1){
            Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();
            nameEdittext.getText().clear();
            codeEdittext.getText().clear();
            typeEdittext.getText().clear();
        }


    }


}