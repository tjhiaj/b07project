//package com.example.b07project;
//
//import static android.content.ContentValues.TAG;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//class ViewReclyerAdapter extends RecyclerView.Adapter<ViewReclyerAdapter.MyViewHolder>{
//    Context context;
//    ArrayList<com.example.b07project.ViewModel> viewModels;
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView subjectView,text1;
//        LinearLayout verticalLayout,horizontalLayout;
//        CardView cardDisplay;
//        Button expandButton;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            subjectView = itemView.findViewById(R.id.subjectView);
//            text1 = itemView.findViewById(R.id.text1);
//           // complaintView = itemView.findViewById(R.id.complaintView);
//            verticalLayout = itemView.findViewById(R.id.verticalLayout);
//            horizontalLayout = itemView.findViewById(R.id.horizontalLayout);
//            cardDisplay = itemView.findViewById(R.id.cardDisplay);
//            expandButton = itemView.findViewById(R.id.expandButton);
//
//        }
//
//    }
//    public ViewReclyerAdapter(Context context, ArrayList<ViewModel> viewModels){
//        this.context = context;
//        this.viewModels = viewModels;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.recycler_row,parent,false);
//        return new ViewReclyerAdapter.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.subjectView.setText(viewModels.get(position).getSubject());
//        holder.text1.setText(viewModels.get(position).getComplaints().toString());
//    }
//
//
//
//
//    @Override
//    public int getItemCount() {
//        //Log.d(TAG, "size"+ viewModels.size());
//        return viewModels.size();
//    }
//
//
//}
