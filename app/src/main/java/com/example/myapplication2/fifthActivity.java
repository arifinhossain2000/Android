package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class fifthActivity<onCreateOptionsMenu> extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private noteadapter noteadapter;
    private List<Modelnote> notelist;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        recyclerView=findViewById(R.id.recycleId);
        floatingActionButton= findViewById(R.id.insertButtonId);
        recyclerView.setLayoutManager(new LinearLayoutManager(fifthActivity.this));
        recyclerView.setHasFixedSize(true);
        notelist = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title();
            }
        });
    }

    private void title() {
        AlertDialog.Builder dailogue = new AlertDialog.Builder(fifthActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.insert_item,null);
        dailogue.setView(view).setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(fifthActivity.this,sixthActivity.class);
                startActivity(intent);

            }
        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                multiAutoCompleteTextView=view.findViewById(R.id.multilineId);

                String note = multiAutoCompleteTextView.getText().toString();
                Modelnote modelnote = new Modelnote(note);
                notelist.add(modelnote);





            }
        }).create().show();
    }



}