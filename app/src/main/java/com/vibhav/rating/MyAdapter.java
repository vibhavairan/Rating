package com.vibhav.rating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ServerValue;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<RatingModel> ratingModels;
    public MyAdapter(Context c, ArrayList<RatingModel> r)
    {
        context = c;
        ratingModels = r;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String r = "Rating: "+ratingModels.get(position).getRating()+"";
        holder.rating.setText(r);
        String d = "Date: "+ratingModels.get(position).getDate()+"";
        holder.date.setText(d);
        String t = "Time: "+ratingModels.get(position).getTime()+"";
        holder.time.setText(t);

    }

    @Override
    public int getItemCount() {
        return ratingModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView rating, date, time;
        public MyViewHolder(View itemView) {
            super(itemView);

            rating = itemView.findViewById(R.id.showRating);
            date = itemView.findViewById(R.id.showDate);
            time = itemView.findViewById(R.id.showTime);
        }
    }

}
