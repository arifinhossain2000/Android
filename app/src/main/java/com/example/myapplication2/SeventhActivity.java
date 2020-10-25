package com.example.myapplication2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SeventhActivity extends AppCompatActivity {

    private Mydatabse mydatabse;
    private RecyclerView recyclerView;
    private FloatingActionButton insertButton;
    private List<Playermodel> playerLIST;
    private PlayerAdapter playerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);



        recyclerView = findViewById(R.id.recycleId);
        insertButton = findViewById(R.id.insertButtonId);


        mydatabse = new Mydatabse(SeventhActivity.this);
        SQLiteDatabase sqLiteDatabase = mydatabse.getWritableDatabase();

        playerAdapter = new PlayerAdapter();

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeventhActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

        loadData();

        playerAdapter.setOnitemclick(new PlayerAdapter.onitemclick() {
            @Override
            public void onsingleclick(int position) {

                String id = "Index:"+playerLIST.get(position).getId();
                String name = "Name:"+playerLIST.get(position).getName();
                String code = "Code:"+playerLIST.get(position).getCode();
                String type = "Type:"+playerLIST.get(position).getType();
                String detalis = id+"\n"+name+"\n"+code+"\n"+type;
                showdetalis(detalis);

            }

            @Override
            public void onLongclick(final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SeventhActivity.this);
                String[] option ={"Delete", "Update"};
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0){

                             deleteData(position);

                        }
                        if(which == 1){

                        }

                    }
                }).create().show();

            }
        });
    }

    private void deleteData(int position) {

        String id = playerLIST.get(position).getId();
         int value = mydatabse.delete(id);
         if(value>0){
             Toast.makeText(this, "Delete Sucessfull", Toast.LENGTH_SHORT).show();
             playerLIST.remove(position);
             playerAdapter.notifyItemRemoved(position);
         }
         else {
             Toast.makeText(this, "No data delete", Toast.LENGTH_SHORT).show();
         }


    }

    private void showdetalis(String detalis) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SeventhActivity.this);
        builder.setTitle("Detalis").setMessage(detalis).setCancelable(true).setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SeventhActivity.this,InsertActivity.class);
                startActivity(intent);

            }
        }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    private void loadData() { // showing data

        playerLIST= new ArrayList<>();
        Cursor cursor = mydatabse.show();

        if(cursor.getCount()== 0){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){

                String id=String.valueOf(cursor.getString(0));
                String Name = cursor.getString(1);
                String Type = cursor.getString(2);
                String Code = String.valueOf(cursor.getString(3));

                Playermodel playermodel= new Playermodel(id,Name,Type,Code);
                playerLIST.add(playermodel);
            }
        }
        playerAdapter.getPlayerList(playerLIST);
        playerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(playerAdapter);
    }
}
