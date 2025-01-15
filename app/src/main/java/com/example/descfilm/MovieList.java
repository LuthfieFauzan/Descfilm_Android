package com.example.descfilm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieList extends RecyclerView.Adapter<MovieList.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, genreyear;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            genreyear = itemView.findViewById(R.id.genreyear);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Movie movie = listMovies.get(position);
                Intent intent = new Intent(context, AdminEditMovie.class);
                intent.putExtra("id", position);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            }
        }
    }

    @Override
    public MovieList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.moviefragment_admin, parent, false);
        return new ViewHolder(view);
    }

    Context context;
    ArrayList<Movie> listMovies;

    public MovieList(Context context, ArrayList<Movie> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    @Override
    public void onBindViewHolder(MovieList.ViewHolder holder, int position) {
        Movie item = listMovies.get(position);
        holder.title.setText(item.getTitle());
        holder.genreyear.setText(item.getYear() + " - " + item.getGenre());
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