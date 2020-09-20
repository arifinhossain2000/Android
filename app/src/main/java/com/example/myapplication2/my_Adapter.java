package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class my_Adapter extends BaseAdapter {

    private String[] playerName;
    private String[] playerType;
    private int[] image;
    private Context context;

    public my_Adapter(String[] playerName, String[] playerType, int[] image, Context context) {
        this.playerName = playerName;
        this.playerType = playerType;
        this.image = image;
        this.context = context;
    }



    @Override
    public int getCount() {
        return playerName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.single_itemlayout,null,false);

        }
        ImageView imageView =view.findViewById(R.id.singleImageId);
        imageView.setImageResource(image[position]);

        TextView name = view.findViewById(R.id.singleNameId);
        name.setText(playerName[position]);

        TextView type = view.findViewById(R.id.singleTypeId);
        type.setText(playerType[position]);



        return view;
    }
}
