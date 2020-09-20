package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class thirdActivity extends AppCompatActivity {

    private ListView listView;
    private String[] playerName;
    private String[] playerType;
    private my_Adapter adapter;
    private Button button;
    private int[] image={R.drawable.messi,R.drawable.shakib,
            R.drawable.neymar,R.drawable.mushfiqur,R.drawable.tamim,
            R.drawable.mark,R.drawable.jamal,R.drawable.siddikur,R.drawable.virat};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        button=findViewById(R.id.fourId);
        listView = findViewById(R.id.listViewId);
        playerName = getResources().getStringArray(R.array.player);
        playerType = getResources().getStringArray(R.array.player_type);
        adapter = new my_Adapter(playerName, playerType, image, thirdActivity.this);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(thirdActivity.this,fourthActivity.class);
                startActivity(intent);
            }
        });

      listView.setOnItemClickListener(new OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              String value = playerName[i];
              Toast.makeText(thirdActivity.this, ""+value, Toast.LENGTH_SHORT).show();
          }
      });
      listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
              AlertDialog.Builder builder = new AlertDialog.Builder(thirdActivity.this);
              builder.setTitle("Delete").setIcon(R.drawable.ic_delete).setMessage("Do you want to delete?")
                      .setCancelable(true).setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {

                  }
              }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      String value = playerName[i];
                      Toast.makeText(thirdActivity.this, "Delete:"+value, Toast.LENGTH_SHORT).show();

                  }
              }).create().show();

              return true;
          }
      });
    }




}


