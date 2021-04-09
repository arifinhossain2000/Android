package com.example.myapplication2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class insert_eightActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText codeEdittext,  nameEdittext;
    private Button insert, list,PICK;
    private Toolbar inserttoolbar;
    private TextView tooltext,typeTextView;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private PlayerData playerData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_eight);

        setupDatabase();

        codeEdittext= findViewById(R.id.insertCodeId);
        nameEdittext=findViewById(R.id.insertNameId);
        insert=findViewById(R.id.insertId);
        PICK=findViewById(R.id.pickButtonId);
        typeTextView= findViewById(R.id.playerTypeTextId);
        list=findViewById(R.id.ViewId);
        inserttoolbar=findViewById(R.id.inserttoolId);
        tooltext=findViewById(R.id.toolTextId);
        tooltext.setText("InsertActivity");
        setSupportActionBar(inserttoolbar);

        insert.setOnClickListener(this);
        list.setOnClickListener(this);
        PICK.setOnClickListener(this);
    }

    private void setupDatabase() {
        playerData= Room.databaseBuilder(insert_eightActivity.this,PlayerData.class,"player_Database")
                .allowMainThreadQueries().build();
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.insertButtonId){

            String name= nameEdittext.getText().toString();
            String code= codeEdittext.getText().toString();
            String type= typeTextView.getText().toString();

            if(name.equals("") || code.equals("") || type.equals("Player Type")|| type.equals("")){

                Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT).show();
            }
            else {
                    insertInfo(name,code,type);
            }

        }
        else if(v.getId()==R.id.ViewId){

            Intent intent= new Intent(insert_eightActivity.this,EightActivity.class);
            startActivity(intent);
            finish();

        }
        else if(v.getId()==R.id.pickButtonId){

            openDialogue();
        }
    }

    private void insertInfo(String name, String code, String type){
        Playermodel player= new Playermodel(name,code,type);
        long value= playerData.playerDao().insertData(player);
        if(value==-1){
            Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();
        }
    }



    private void openDialogue() {

        AlertDialog.Builder builder= new AlertDialog.Builder(insert_eightActivity.this);
        LayoutInflater inflater= this.getLayoutInflater();
        final View view= inflater.inflate(R.layout.player_type,null);
        builder.setView(view).setTitle("Choose a Item").setCancelable(true)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        radioGroup= view.findViewById(R.id.radioGroupId);
                        int select= radioGroup.getCheckedRadioButtonId();
                        radioButton= view.findViewById(select);
                        typeTextView.setText(radioButton.getText().toString());
                    }
                }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }
}