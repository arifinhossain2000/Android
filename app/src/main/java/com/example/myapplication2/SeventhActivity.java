package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
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
    private RelativeLayout relativeLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        relativeLayout=findViewById(R.id.relativeId);




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

                Playermodel detalis = playerLIST.get(position);
                Intent intent = new Intent(SeventhActivity.this,DetalisActivity.class);
                intent.putExtra("detalis",detalis);
                startActivity(intent);
            }

            @Override
            public void onLongclick(final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SeventhActivity.this);
                builder.setTitle("Delete").setMessage("Do u want to delete?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteData(position);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.palyer_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searhId);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                playerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

   // @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(item.getItemId()==R.id.darkId ){
//            relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorDark));
//
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
