package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.Myviewholder> {

    private List<Model> playerList;

    public myAdapter(List<Model> playerList) {
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_itemlayout,parent,false);

        return  new Myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

        holder.imageView.setImageResource(playerList.get(position).getPosition());
        holder.textView.setText(playerList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

        private CircleImageView imageView;
        private TextView textView;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageId);
            textView=itemView.findViewById(R.id.singleNameId);
        }
    }
}
