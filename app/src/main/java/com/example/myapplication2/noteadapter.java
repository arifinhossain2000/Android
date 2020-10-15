package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class noteadapter extends RecyclerView.Adapter<noteadapter.viewholder> {

    private List<Modelnote> notelist;

    private void getnote(List<Modelnote> notelist) {
        this.notelist = notelist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem,parent,false);
        return  new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.note.setText(notelist.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        private MultiAutoCompleteTextView note;

    public viewholder(@NonNull View itemView) {
        super(itemView);

        note = itemView.findViewById(R.id.singlenoteId);
    }
}

}
