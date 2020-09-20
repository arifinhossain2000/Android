package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button buttonOne, buttonTwo, buttonThree;
    private Spinner spinner;
    private ImageView imageView;
    private String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = getResources().getStringArray(R.array.Country);
        buttonOne =findViewById(R.id.buttonOneId);
        buttonTwo =findViewById(R.id.buttonTwoId);
        buttonThree =findViewById(R.id.buttonId);
        imageView = findViewById(R.id.imageId);
        spinner   =findViewById(R.id.spinnerId);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String text= adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, ""+text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();

            }
        });
    }

    private void pickImage() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

            }
            else{
                uploadImage();
            }

        }
        else {

            uploadImage();
        }
    }

    private void uploadImage() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1).start(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.buttonOneId){

            Intent intent = new Intent(MainActivity.this, secondActivity.class);
            intent.putExtra("key", "one");
            startActivity(intent);


        }
        else if(view.getId()==R.id.buttonTwoId){

            Intent intent = new Intent(MainActivity.this, secondActivity.class);
            intent.putExtra("key", "Two");
            startActivity(intent);
        }
    }
}