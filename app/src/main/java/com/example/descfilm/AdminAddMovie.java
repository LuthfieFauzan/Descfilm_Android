package com.example.descfilm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

public class AdminAddMovie extends AppCompatActivity {
    EditText movieid,title, genre, language, runtime, producer, director, distributor, year,
            rating, casts, synopsisshort, synopsisline1, synopsisline2, videourl;
    Button add, cancel;
    DatabaseReference mDatabase;

    Movie movie = new Movie();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_addmovie);

        movieid = findViewById(R.id.movieid);
        title = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        language = findViewById(R.id.language);
        runtime = findViewById(R.id.runtime);
        producer = findViewById(R.id.producer);
        director = findViewById(R.id.director);
        distributor = findViewById(R.id.distributor);
        year = findViewById(R.id.year);
        rating = findViewById(R.id.rating);
        casts = findViewById(R.id.casts);
        synopsisshort = findViewById(R.id.synopsisshort);
        synopsisline1 = findViewById(R.id.synopsisline1);
        synopsisline2 = findViewById(R.id.synopsisline2);
        videourl = findViewById(R.id.videourl);

        AutoCompleteTextView autogenre;
        String[] arr = {"Comedy","Adventure","Action","Animation","Drama"};
        autogenre = findViewById(R.id.genre);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, arr);
        autogenre.setThreshold(1);
        autogenre.setAdapter(adapter);

        AutoCompleteTextView autolanguage;
        String[] arr2 = {"English","Indonesian","Japanese","Mandarin","Korean"};
        autolanguage = findViewById(R.id.language);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, arr2);
        autolanguage.setThreshold(1);
        autolanguage.setAdapter(adapter2);

        AutoCompleteTextView autorating;
        String[] arr3 = {"E","PG","PG-13","R"};
        autorating = findViewById(R.id.rating);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, arr3);
        autorating.setThreshold(1);
        autorating.setAdapter(adapter3);

        add = findViewById(R.id.addmovie);
        mDatabase= FirebaseDatabase.getInstance().getReference("movie");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sMovieid = movieid.getText().toString();
                final String sTitle = title.getText().toString();
                final String sGenre = genre.getText().toString();
                final String sLanguage = language.getText().toString();
                final String sRuntime = runtime.getText().toString();
                final String sProducer = producer.getText().toString();
                final String sDirector = director.getText().toString();
                final String sDistributor= distributor.getText().toString();
                final String sYear = year.getText().toString();
                final String sRating = rating.getText().toString();
                final String sCasts = casts.getText().toString();
                final String sSynopsissort = synopsisshort.getText().toString();
                final String sSynopsisline1 = synopsisline1.getText().toString();
                final String sSynopsisline2 = synopsisline2.getText().toString();
                final String sVideourl = videourl.getText().toString();
                getVelue();

                if (sMovieid.equals("") || sTitle.equals("") || sGenre.equals("") ||
                        sLanguage.equals("") || sRuntime.equals("") || sProducer.equals("") ||
                        sDirector.equals("") || sDistributor.equals("") || sYear.equals("") ||
                        sRating.equals("") || sCasts.equals("") || sSynopsissort.equals("") ||
                        sSynopsisline1.equals("") || sVideourl.equals("")) {
                    Toast.makeText(AdminAddMovie.this,
                            "Please input all required fields before continue!",
                            Toast.LENGTH_LONG).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(AdminAddMovie.this);
                    builder.setTitle("Proceed to add " + sTitle + " as a new movie?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mDatabase.child(sMovieid).setValue(movie);
                            Toast.makeText(AdminAddMovie.this,
                                    "A new movie, " + sTitle + ", has been added successfully.",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    });
                    builder.create().show();
                }
            }
        });

        cancel= findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getVelue() {
        movie.setTitle(title.getText().toString());
        movie.setGenre(genre.getText().toString());
        movie.setLanguage(language.getText().toString());
        movie.setRuntime(runtime.getText().toString());
        movie.setProducer(producer.getText().toString());
        movie.setDirector(director.getText().toString());
        movie.setDistributor(distributor.getText().toString());
        movie.setYear(year.getText().toString());
        movie.setRating(rating.getText().toString());
        movie.setCasts(casts.getText().toString());
        movie.setSynopsisshort(synopsisshort.getText().toString());
        movie.setSynopsisline1(synopsisline1.getText().toString());
        movie.setSynopsisline2(synopsisline2.getText().toString());
        movie.setVideourl(videourl.getText().toString());
    }
}