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
    private clickInterface clickInterface;

    public myAdapter(List<Model> playerList,clickInterface clickInterface) {
        this.playerList = playerList;
        this.clickInterface = clickInterface;
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
        private TextView type;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.singleImageId);
            textView=itemView.findViewById(R.id.singleNameId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickInterface.onLongClick(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
