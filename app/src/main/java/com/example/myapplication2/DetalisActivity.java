package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalisActivity extends AppCompatActivity {

    private TextView Nametext, Idtext, Codetext, Typetext;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis);

        setTitle("Detalis");
        final Playermodel detalismodel = (Playermodel) getIntent().getSerializableExtra("detalis");

        Nametext=findViewById(R.id.detalisName);
        Idtext=findViewById(R.id.detalisId);
        Codetext=findViewById(R.id.detalisCode);
        Typetext=findViewById(R.id.detalisType);
        update=findViewById(R.id.updateButtonid);

        Nametext.setText("Name:"+detalismodel.getName());
        Idtext.setText("Id:"+detalismodel.getId());
        Typetext.setText("Type:"+detalismodel.getType());
        Codetext.setText("Code:"+detalismodel.getCode());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetalisActivity.this, UpdateActivity.class);
                intent.putExtra("update",detalismodel);
                startActivity(intent);

            }
        });
    }
}