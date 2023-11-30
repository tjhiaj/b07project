package com.example.b07project;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnnouncementAdapterDismissable extends RecyclerView.Adapter<AnnouncementAdapterDismissable.AnnouncementViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDismissalClick(int position); // New interface method for dismiss button click

    }

    private OnItemClickListener listener;
    private List<Announcement> announcementList;
    private Context context;

    public AnnouncementAdapterDismissable(Context context, List<Announcement> announcementList) {
        this.context = context;
        this.announcementList = announcementList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dismissable_announcements, parent, false);
        return new AnnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        Announcement announcement = announcementList.get(position);

        holder.subjectTextView.setText(announcement.getSubject());
        holder.messageTextView.setText(announcement.getMessage());

        holder.dismissButton.setOnClickListener(view -> {
            if (listener != null) {
                listener.onDismissalClick(position);
            }
        });

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        ImageView trumpetIcon;
        TextView subjectTextView;
        TextView messageTextView;
        Button dismissButton;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            trumpetIcon = itemView.findViewById(R.id.trumpetIcon);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            dismissButton = itemView.findViewById(R.id.dismissalButton);
        }
    }

}

