package com.example.descfilm;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private DatabaseReference mReferenceMovie;
    private FirebaseDatabase mDatabase;
    private List<Movie> movie = new ArrayList<>();

    public interface DataStatus {
        void dataIsLoad(List<Movie> movie,List<String> keys);
        void dataIsUpdate();
        void dataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceMovie = mDatabase.getReference("movie");
    }

    public void readMovie(final DataStatus dataStatus) {
        mReferenceMovie.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                movie.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Movie Movi = keyNode.getValue(Movie.class);
                    movie.add(Movi);
                }
                dataStatus.dataIsLoad(movie,keys);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void updateMovi(String key,Movie movie, final DataStatus dataStatus) {
        mReferenceMovie.child(key).setValue(movie).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsUpdate();
            }
        });
    }

    public void deleteMovi(String key,final DataStatus dataStatus) {
        mReferenceMovie.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsDeleted();
            }
        });
    }
}