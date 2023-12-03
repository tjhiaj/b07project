package com.example.b07project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String subject = (String) viewModels.get(position).keySet().toArray()[0];
        holder.subjectView.setText(viewModels.get(position).get(subject));
        holder.complaintText.setText(subject);

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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subjectView, complaintText;
        LinearLayout verticalLayout;
        CardView cardDisplay;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectView = itemView.findViewById(R.id.subject1);
            complaintText = itemView.findViewById(R.id.complaintText);
            verticalLayout = itemView.findViewById(R.id.verticalLayout);
            cardDisplay = itemView.findViewById(R.id.card3);
        }

    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }
}
