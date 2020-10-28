package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private TextView idView;
    private EditText nameEdit, codeEdit, typeEdit;
    private Button updateButton;
    private Mydatabse mydatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mydatabse = new Mydatabse(UpdateActivity.this);
        SQLiteDatabase sqLiteDatabase = mydatabse.getWritableDatabase();

        final Playermodel updateModel= (Playermodel) getIntent().getSerializableExtra("update");


        idView=findViewById(R.id.updateId);
        nameEdit =findViewById(R.id.updateName);
        codeEdit=findViewById(R.id.updateCode);
        typeEdit=findViewById(R.id.updateType);
        updateButton=findViewById(R.id.viewUpdate);

        idView.setText(updateModel.getId());
        nameEdit.setText(updateModel.getName());
        codeEdit.setText(updateModel.getCode());
        typeEdit.setText(updateModel.getType());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = updateModel.getId();
                String name = nameEdit.getText().toString();
                String code = codeEdit.getText().toString();
                String type = typeEdit.getText().toString();

                updateData(id,name,code,type);






            }
        });


    }

    private void updateData(String id, String name, String code, String type) {

        long isupdate =mydatabse.update(id,name,code,type);
        if(isupdate == -1){
            Toast.makeText(this, "No update", Toast.LENGTH_SHORT).show();
        }
        else if(isupdate == 0){
            Toast.makeText(this, "No update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateActivity.this,SeventhActivity.class);
            startActivity(intent);
            finish();
        }
    }
}