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

import java.util.List;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintViewHolder> {

    List<Complaints> Complaints;
    Context context;
    LinearLayout verticalLayout, horizontalLayout;

    public ComplaintsAdapter(Context context, List<Complaints> Complaints) {
        this.context = context;
        this.Complaints = Complaints;
    }

    @NonNull
    @Override
    public ComplaintsAdapter.ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ComplaintsAdapter.ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintsAdapter.ComplaintViewHolder holder, int position) {
        Complaints complaint = Complaints.get(position);

        holder.subjectView.setText(complaint.getSubject());
        holder.messageView.setText(complaint.getComplaints().toString());
    }

    @Override
    public int getItemCount() {
        return Complaints.size();
    }

    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        ImageView messageBottleIcon;
        TextView subjectView;
        TextView messageView;
        CardView cardDisplay;
        TextView title;
        TextView expand;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            expand = itemView.findViewById(R.id.expand);
            title = itemView.findViewById(R.id.title);
            cardDisplay = itemView.findViewById(R.id.card3);
            messageBottleIcon = itemView.findViewById(R.id.message_Icon);
            subjectView = itemView.findViewById(R.id.subjectTextView);
            messageView = itemView.findViewById(R.id.messageTextView);
        }
    }
}


