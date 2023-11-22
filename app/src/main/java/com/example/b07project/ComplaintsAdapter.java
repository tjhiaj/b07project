package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintViewHolder> {

    ArrayList<com.example.b07project.Complaints> Complaints;
    Context context;
    LinearLayout verticalLayout, horizontalLayout;
    CardView cardDisplay;

    public ComplaintsAdapter(Context context, ArrayList<Complaints> complaintsModel) {
        this.context = context;
        this.Complaints = complaintsModel;
    }

    @NonNull
    @Override
    public ComplaintsAdapter.ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view, parent, false);
        return new ComplaintsAdapter.ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintsAdapter.ComplaintViewHolder holder, int position) {
        Complaints complaint = Complaints.get(position);

        holder.subjectView.setText(Complaints.get(position).getSubject());
        holder.messageView.setText(Complaints.get(position).getComplaints().toString());
    }

    @Override
    public int getItemCount() {
        return Complaints.size();
    }

    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        ImageView messageBottleIcon;
        TextView subjectView;
        TextView messageView;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            messageBottleIcon = itemView.findViewById(R.id.trumpetIcon);
            subjectView = itemView.findViewById(R.id.subjectTextView);
            messageView = itemView.findViewById(R.id.messageTextView);
        }
    }
}


