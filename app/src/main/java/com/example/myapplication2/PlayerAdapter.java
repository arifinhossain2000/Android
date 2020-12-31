package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.playerViewholder>{

    private List<Playermodel> playerList;
    private List<Playermodel> searchList;
    public Filter playerFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Playermodel> filterUser = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterUser.addAll(searchList);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Playermodel playermodel : searchList){
                    if(playermodel.getCode().toLowerCase().contains(constraint) || playermodel.getName().toLowerCase().contains(constraint)){

                        filterUser.add(playermodel);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterResults;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            playerList.clear();
            playerList.addAll((List<Playermodel>)results.values);
            notifyDataSetChanged();

        }
    };
    private onitemclick onitemclick;

    public  void getPlayerList(List<Playermodel> playerList){
        this.playerList=playerList;
        searchList = new ArrayList<>(playerList);
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

    public Filter getFilter(){
        return playerFilter;
    }

    public void  setOnitemclick(onitemclick onitemclick){
        this.onitemclick = onitemclick;
    }

    public interface onitemclick {
        void onsingleclick(int position);
        void onLongclick(int position);
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


}
