package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("arifin", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("status")){
            String value = sharedPreferences.getString("status","notlogin");
            if(value.equals("login")){
                Intent intent = new Intent(StartActivity.this, SeventhActivity.class);
                startActivity(intent);
                finish();
            }
            else if(value.equals("notlogin")){
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        }
        else {
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


    }
}