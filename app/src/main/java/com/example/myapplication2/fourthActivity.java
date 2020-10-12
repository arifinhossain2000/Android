package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class fourthActivity extends AppCompatActivity implements clickInterface{

    private String[] playerName,playerType;
    List<Model> playerList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Model model;
    private myAdapter adapter;
    private String  deletePlayer;
    private Button button;

    private int[] image={R.drawable.messi,R.drawable.shakib,
            R.drawable.neymar,R.drawable.mushfiqur,R.drawable.tamim,
            R.drawable.mark,R.drawable.jamal,R.drawable.siddikur,R.drawable.virat};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        button=findViewById(R.id.fifthButton);
        playerList= new ArrayList<>();
        playerName = getResources().getStringArray(R.array.player);
        playerType = getResources().getStringArray(R.array.player_type);
        recyclerView=findViewById(R.id.recycleId);
        refreshLayout=findViewById(R.id.refreshId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fourthActivity.this,fifthActivity.class);
                startActivity(intent);
            }
        });


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(fourthActivity.this, "You get all data", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });

        ItemTouchHelper itemTouchHelper =new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        for(int i=0; i<playerName.length;i++){
            model = new Model(image[i],playerName[i],playerType[i]);
            playerList.add(model);
        }

        adapter= new myAdapter(playerList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(fourthActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.START
                    |ItemTouchHelper.END,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int frompostion = viewHolder.getAdapterPosition();
            int topoosition = target.getAdapterPosition();
            Collections.swap(playerList,frompostion,topoosition);
            recyclerView.getAdapter().notifyItemMoved(frompostion,topoosition);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();

            if(direction== ItemTouchHelper.LEFT || direction== ItemTouchHelper.RIGHT){
                deletePlayer = playerList.get(position).getName();
                final Model mymodel = playerList.get(position);
                playerList.remove(position);
                adapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, deletePlayer+" is deleted",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model = new Model(image[position],deletePlayer,playerType[position]);
                        playerList.add(position,mymodel);
                        adapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                   .addSwipeLeftBackgroundColor(ContextCompat.getColor(fourthActivity.this,R.color.colorRed))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftLabel("Delete")
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(fourthActivity.this,R.color.colorRed))
                    .addSwipeRightActionIcon(R.drawable.ic_delete)
                    .addSwipeRightLabel("Delete")
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

        @Override
    public void onItemClick(int position) {
        Toast.makeText(this, ""+playerList.get(position).getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLongClick(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fourthActivity.this);
        builder.setTitle("Delete").setIcon(R.drawable.ic_delete).setMessage("Do you want to delete?")
                .setCancelable(true).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                playerList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }).create().show();
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuitem,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId()== R.id.gridId){
                Toast.makeText(this, "nO", Toast.LENGTH_SHORT).show();
            }
            if(item.getItemId()== R.id.contectId){
                Intent intent = new Intent(fourthActivity.this,contectActivity.class);
                startActivity(intent);

            }
            if(item.getItemId()==R.id.aboutId){
                Toast.makeText(this, "Not applied", Toast.LENGTH_SHORT).show();
            }


        return super.onOptionsItemSelected(item);
    }
}
