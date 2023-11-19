package com.example.b07project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {

    private List<Announcement> announcementList;
    private Context context;

    public AnnouncementAdapter(Context context, List<Announcement> announcementList) {
        this.context = context;
        this.announcementList = announcementList;
    }

    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, parent, false);
        return new AnnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        Announcement announcement = announcementList.get(position);

        holder.subjectTextView.setText(announcement.getSubject());
        holder.messageTextView.setText(announcement.getMessage());
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        ImageView trumpetIcon;
        TextView subjectTextView;
        TextView messageTextView;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            trumpetIcon = itemView.findViewById(R.id.trumpetIcon);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}

