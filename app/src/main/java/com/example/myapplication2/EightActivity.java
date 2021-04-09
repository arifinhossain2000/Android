package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EightActivity extends AppCompatActivity {

    private  Toolbar mainToolbar;
    private TextView toolText;
    private FloatingActionButton insertButton;
    private RecyclerView recyclerView;
    private PlayerData playerData;
    private PlayerAdapter adapter;
    private List<Playermodel> playerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);
        setupDB();


        insertButton=findViewById(R.id.insertButtonId);
        mainToolbar=findViewById(R.id.mainToolbarId);
        toolText=findViewById(R.id.toolTextId);
        toolText.setText("Player List");
        setSupportActionBar(mainToolbar);

        recyclerView=findViewById(R.id.recycleViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(EightActivity.this));
        recyclerView.setHasFixedSize(true);

        adapter= new PlayerAdapter();
        playerList= new ArrayList<>();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EightActivity.this, insert_eightActivity.class);
                startActivity(intent);
            }
        });
        loadData();
        adapter.setOnitemclick(new PlayerAdapter.onitemclick() {
            @Override
            public void onsingleclick(int position) {

                Playermodel playermodel = playerList.get(position);
                Intent intent = new Intent(EightActivity.this, detalisActivity.class);
                intent.putExtra("detalis",playermodel);
                startActivity(intent);
            }

            @Override
            public void onLongclick(View view) {

            }
        });
    }

    private void loadData() {

        playerList= playerData.playerDao().readData();
        adapter.getPlayerList(playerList);
        recyclerView.setAdapter(adapter);

    }

    private void setupDB() {

        playerData = Room.databaseBuilder(EightActivity.this, PlayerData.class,"Player Database").allowMainThreadQueries().build();


    }


}