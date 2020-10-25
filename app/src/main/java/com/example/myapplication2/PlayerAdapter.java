package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.playerViewholder>{

    private List<Playermodel> playerList;
    private onitemclick onitemclick;

    public  void getPlayerList(List<Playermodel> playerList){
        this.playerList=playerList;
    }

    @NonNull
    @Override
    public playerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_player,parent,false);

        return new playerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull playerViewholder holder, int position) {

        holder.singleCode.setText("Code:"+playerList.get(position).getCode());
        holder.singleName.setText("Name:"+playerList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public  class  playerViewholder extends RecyclerView.ViewHolder{

        private TextView singleName, singleCode;



        public playerViewholder(@NonNull View itemView) {
            super(itemView);

            singleName= itemView.findViewById(R.id.singlenameId);
            singleCode = itemView.findViewById(R.id.singlecodeId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if(onitemclick != null && position!=RecyclerView.NO_POSITION){
                        onitemclick.onsingleclick(position);
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int position = getAdapterPosition();

                    if(onitemclick != null && position!=RecyclerView.NO_POSITION){
                        onitemclick.onLongclick(position);
                    }
                    return false;
                }
            });
        }
    }

    public interface onitemclick {
        void onsingleclick(int position);
        void onLongclick(int position);
    }

    public void  setOnitemclick(onitemclick onitemclick){
        this.onitemclick = onitemclick;
    }


}
