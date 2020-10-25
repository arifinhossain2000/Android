package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class sixthActivity extends AppCompatActivity {

    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private RecyclerView recyclerView;
    private noteadapter noteadapter;
    private List<Modelnote> notelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        noteadapter = new noteadapter();
        notelist = new ArrayList<>();

        multiAutoCompleteTextView=findViewById(R.id.multilineId);
        recyclerView=findViewById(R.id.recycleId);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note,menu);

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.submiId) {

            /*multiAutoCompleteTextView=view.findViewById(R.id.multilineId);
            String note = multiAutoCompleteTextView.getText().toString();
            Modelnote modelnote = new Modelnote(note);
            notelist.add(modelnote);*/

            Intent  intent= new Intent(sixthActivity.this, fifthActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

}