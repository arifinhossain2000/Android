package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class detalisActivity extends AppCompatActivity {

    private TextView indexText,nameText,codeText,typeText;
    private Button updateButton;
    private Toolbar detailsToolbar;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis);

        final Playermodel detailsPlayer= (Playermodel) getIntent().getSerializableExtra("details");

        detailsToolbar= findViewById(R.id.detalistoolId);
        toolbarText= findViewById(R.id.toolTextId);
        toolbarText.setText("Details");
        setSupportActionBar(detailsToolbar);

        indexText= findViewById(R.id.detalisId);
        nameText= findViewById(R.id.detalisName);
        codeText= findViewById(R.id.detalisCode);
        typeText= findViewById(R.id.detalisType);
        updateButton= findViewById(R.id.updateButtonid);

        //set Text.....

        indexText.setText("Index ID: "+Integer.toString(detailsPlayer.getId()));
        nameText.setText("Name: "+detailsPlayer.getName());
        codeText.setText("Code: "+detailsPlayer.getCode());
        typeText.setText("Type: "+detailsPlayer.getType());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(detalisActivity.this,updateActivity.class);
                intent.putExtra("update",detailsPlayer);
                startActivity(intent);

            }
        });



    }
}
}