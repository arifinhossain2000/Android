package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class contectActivity extends AppCompatActivity {

    private EditText phoneEDItText, toEDItText,subEDItText, messageEDItText;
    private ImageView phoneImage, mailImage;
    public static final int request_call = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contect);
        phoneEDItText = findViewById(R.id.phoneEditTextId);
        toEDItText = findViewById(R.id.toEditTextId);
        subEDItText = findViewById(R.id.subEditTextId);
        messageEDItText = findViewById(R.id.messageEditTextId);
        phoneImage = findViewById(R.id.phoneImageId);
        mailImage = findViewById(R.id.mailImageId);

        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makephone();

            }
        });

        mailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentmail();

            }
        });
    }

    private void sentmail() {
        String to = toEDItText.getText().toString();
        String[] maketo = to.split(",");
        String subject = subEDItText.getText().toString();
        String message = messageEDItText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,maketo);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Hello"));
    }

    private void makephone() {
        String number = phoneEDItText.getText().toString();
        if (number.trim().length() > 0) {
            if(ContextCompat.checkSelfPermission(contectActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(contectActivity.this, new String[]{Manifest.permission.CALL_PHONE}, request_call);

            }
            else {
                String dail = "tel:"+number;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dail));
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(this, "Please Enter Right NUmber", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==request_call){
            if(grantResults.length>0  && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makephone();
            }
        }
        else {
            Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

