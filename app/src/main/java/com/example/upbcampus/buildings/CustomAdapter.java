package com.example.upbcampus.buildings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.upbcampus.R;

import java.util.ArrayList;

/**
 * This is a CustomAdapter used to display the buildings.
 * One model has an ImageView and a TextView.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.imageViewIcon = itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        imageView.setImageResource(dataSet.get(listPosition).getImage());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("You clicked on image.Redirecting...");
                System.out.println(dataSet.get(listPosition).getName());
                switch(dataSet.get(listPosition).getName()) {
                    case "EC":
                        Intent ecIntent = new Intent(context, EcActivity.class);
                        context.startActivity(ecIntent);
                        break;
                    case "ED":
                        Intent edIntent = new Intent(context, EdActivity.class);
                        context.startActivity(edIntent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
