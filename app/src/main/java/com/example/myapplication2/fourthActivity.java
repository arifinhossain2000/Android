package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fourthActivity extends AppCompatActivity {

    private String[] playerName;
    List<Model> playerList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Model model;
    private myAdapter adapter;
    private int[] image={R.drawable.messi,R.drawable.shakib,
            R.drawable.neymar,R.drawable.mushfiqur,R.drawable.tamim,
            R.drawable.mark,R.drawable.jamal,R.drawable.siddikur,R.drawable.virat};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        playerList= new ArrayList<>();
        playerName = getResources().getStringArray(R.array.player);
        recyclerView=findViewById(R.id.recycleId);
        refreshLayout=findViewById(R.id.refreshId);

        for(int i=0; i<playerName.length;i++){
            model = new Model(image[i],playerName[i]);
            playerList.add(model);
        }

        adapter= new myAdapter(playerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(fourthActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);




    }
}
