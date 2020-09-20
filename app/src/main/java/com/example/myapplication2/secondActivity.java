package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class secondActivity extends AppCompatActivity {

    private TextView textView;
    private Button dateButton, timeButton,image,listButton;
    private TextView dateText, timeText;
    private ImageView imageView;
    public static final int Gallery_Request=1;
    public static final int Camera_Request=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        textView = findViewById(R.id.textId);
        dateButton = findViewById(R.id.dateButtonId);
        timeButton = findViewById(R.id.timeButtonId);
        dateText = findViewById(R.id.dateTextId);
        timeText = findViewById(R.id.timeTextId);
        image =findViewById(R.id.pickImageId);
        listButton =findViewById(R.id.showId);
        imageView =findViewById(R.id.imageId);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(secondActivity.this,thirdActivity.class);
                startActivity(intent);
            }
        });
        String value = getIntent().getStringExtra("key");

        if (value.equals("one")) {

            textView.setText("Come from button one");
        }
        else {
            textView.setText("Come from button two");
        }

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = new TimePicker(secondActivity.this);

                int hour = timePicker.getHour();
                int min = timePicker.getMinute();

                TimePickerDialog dialog = new TimePickerDialog(secondActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {

                        dateText.setText("Time:" +hourofDay+ ":" + minute);
                    }
                }, hour, min, false);
                dialog.show();
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = new DatePicker(secondActivity.this);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                DatePickerDialog dialog = new DatePickerDialog(secondActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {

                        int a = month + 1;
                        dateText.setText("Date:" + dayofMonth+"/"+ a +"/"+ year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogue();
            }
        });
    }
    private void openDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(secondActivity.this);
        String []option={"Gallery","Camera"};
        builder.setItems(option,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(i==0){
                            openGallary();
                        }
                        if(i==1){
                            openCamera();
                        }
                    }
                }).create().show();
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,Camera_Request);
    }

    private void openGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Gallery_Request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Request && RESULT_OK == resultCode){
            Uri uri= data.getData();
            imageView.setImageURI(uri);
        }
        if(requestCode==Camera_Request && RESULT_OK == resultCode){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

        }

    }


}


