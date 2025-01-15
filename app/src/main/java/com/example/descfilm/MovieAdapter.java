package com.example.descfilm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, genreyear;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            genreyear = itemView.findViewById(R.id.genreyear);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Movie movie = listMovies.get(position);
                Intent intent = new Intent(context, ViewMovieDetail.class);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            }
        }
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.moviefragment, parent, false);
        return new ViewHolder(view);
    }

    Context context;
    ArrayList<Movie> listMovies;

    public MovieAdapter(Context context, ArrayList<Movie> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie item = listMovies.get(position);
        holder.title.setText(item.getTitle());
        holder.genreyear.setText(item.getYear() + " - " + item.getGenre());
        Glide.with(context)
                .load("https://firebasestorage.googleapis.com/v0/b/realtime-db-web-f1fff.appspot.com/o/img%2F"+item.getImage()+"?alt=media")
                .into(holder.image);
    }

    public void remove(int position) {
        listMovies.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Movie text, int position) {
        listMovies.add(position, text);
        notifyItemInserted(position);
    }
}