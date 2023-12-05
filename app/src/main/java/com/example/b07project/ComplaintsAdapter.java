package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintsViewHolder> {

    List<Complaints> complaints;
    Context context;


    public ComplaintsAdapter(Context context, List<Complaints> Complaints) {
        this.context = context;
        this.complaints = Complaints;
    }

    @NonNull
    @Override
    public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ComplaintsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ComplaintsViewHolder holder, int position) {
        Complaints complaint = complaints.get(position);
        holder.subjectView.setText(complaint.getSubject());
        holder.complaintText.setText(complaint.getComplaints());
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public class ComplaintsViewHolder extends RecyclerView.ViewHolder {
        ImageView messageBottleIcon;
        TextView subjectView, title;
        TextView complaintText;
        LinearLayout expandableView;

        public ComplaintsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.anonymous);
            messageBottleIcon = itemView.findViewById(R.id.message_Icon);
            subjectView = itemView.findViewById(R.id.subject1);
            expandableView = itemView.findViewById(R.id.expandableView);
            complaintText = itemView.findViewById(R.id.complaintText);
        }

    }
}


