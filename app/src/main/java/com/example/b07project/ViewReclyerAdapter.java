package com.example.b07project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

class ViewReclyerAdapter extends RecyclerView.Adapter<ViewReclyerAdapter.MyViewHolder>{
    Context context;
    ArrayList<Map<String,String>> viewModels;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView subjectView,text1;
        LinearLayout verticalLayout;
        CardView cardDisplay;
        Button expandButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectView = itemView.findViewById(R.id.subject1);
            text1 = itemView.findViewById(R.id.text1);
            verticalLayout = itemView.findViewById(R.id.verticalLayout);
            cardDisplay = itemView.findViewById(R.id.card3);
            expandButton = itemView.findViewById(R.id.expand);
        }

    }
    public ViewReclyerAdapter(Context context, ArrayList<Map<String,String>> viewModels){
        this.context = context;
        this.viewModels = viewModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item,parent,false);
        return new ViewReclyerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String subject = (String) viewModels.get(position).keySet().toArray()[0];
        holder.subjectView.setText(subject);
        holder.text1.setText(viewModels.get(position).get(subject));
    }




    @Override
    public int getItemCount() {
        return viewModels.size();
    }


}

