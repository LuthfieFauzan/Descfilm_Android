package com.example.descfilm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.*;

import java.util.ArrayList;

public class AdminMovieList extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rcvFilms;
    MovieList adapterFilm;
    ArrayList<Movie> arrFilms;
    DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_movielist);

        initFirebase();
        addWidgets();
        setEventsWidget();
        initRecyclerView();
        retrieveFilms();

        findViewById(R.id.addmovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addmovie = new Intent(AdminMovieList.this, AdminAddMovie.class);
                startActivity(addmovie);
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AdminMovieList.this);
                builder.setTitle("Do you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Preferences.clearLoggedInUser(getBaseContext());
                        startActivity(new Intent(getBaseContext(),MainMenu.class));
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                builder.create().show();
            }
        });
    }

    private void addWidgets() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    private void setEventsWidget() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListFilms();
            }
        });
    }

    private void refreshListFilms() {
        mDatabase.child("movie").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) return;
                arrFilms.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    arrFilms.add(item.getValue(Movie.class));
                }
                adapterFilm.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void initFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void initRecyclerView() {
        rcvFilms = findViewById(R.id.rcvFilms);
        arrFilms = new ArrayList<Movie>();
        adapterFilm = new MovieList(this, arrFilms);
        rcvFilms.setHasFixedSize(true);
        rcvFilms.setLayoutManager(new LinearLayoutManager(this));
        rcvFilms.setAdapter(adapterFilm);
    }

    private void retrieveFilms() {
        mDatabase.child("movie").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot == null) return;
                arrFilms.add(dataSnapshot.getValue(Movie.class));
                adapterFilm.notifyItemInserted(arrFilms.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}