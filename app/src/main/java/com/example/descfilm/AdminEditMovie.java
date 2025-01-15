package com.example.descfilm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminEditMovie extends AppCompatActivity {
    EditText movieid, title, genre, language, runtime, producer, director, distributor, year,
            rating, casts, synopsisshort, synopsisline1, synopsisline2, videourl;
    Button edit, delete, cancel;
    int id;
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_editmovie);

        final Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        id = getIntent().getExtras().getInt("id") + 1;
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

        movieid.setText(Integer.toString(id));
        test = movieid.getText().toString();
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        language.setText(movie.getLanguage());
        runtime.setText(movie.getRuntime());
        producer.setText(movie.getProducer());
        director.setText(movie.getDirector());
        distributor.setText(movie.getDistributor());
        year.setText(movie.getYear());
        rating.setText(movie.getRating());
        casts.setText(movie.getCasts());
        synopsisshort.setText(movie.getSynopsisshort());
        synopsisline1.setText(movie.getSynopsisline1());
        synopsisline2.setText(movie.getSynopsisline2());
        videourl.setText(movie.getVideourl());

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

        edit = findViewById(R.id.editmovie);
        edit.setOnClickListener(new View.OnClickListener() {
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

                if (sMovieid.equals("") || sTitle.equals("") || sGenre.equals("") ||
                        sLanguage.equals("") || sRuntime.equals("") || sProducer.equals("") ||
                        sDirector.equals("") || sDistributor.equals("") || sYear.equals("") ||
                        sRating.equals("") || sCasts.equals("") || sSynopsissort.equals("") ||
                        sSynopsisline1.equals("") || sVideourl.equals("")) {
                    Toast.makeText(AdminEditMovie.this,
                            "Please input all required fields before continue!",
                            Toast.LENGTH_LONG).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditMovie.this);
                    builder.setTitle("Proceed to update " + sTitle + " movie data?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new FirebaseDatabaseHelper().updateMovi(test, movie, new FirebaseDatabaseHelper.DataStatus() {
                                @Override
                                public void dataIsLoad(List<Movie> movie, List<String> keys) {}

                                @Override
                                public void dataIsUpdate() {
                                    Toast.makeText(AdminEditMovie.this,
                                            sTitle + " movie data are updated successfully.",
                                            Toast.LENGTH_LONG).show();
                                    Intent list = new Intent(AdminEditMovie.this, AdminMovieList.class);
                                    startActivity(list);
                                }

                                @Override
                                public void dataIsDeleted() {}
                            });
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

        delete = findViewById(R.id.deletemovie);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sMovieid = movieid.getText().toString();
                final String sTitle = title.getText().toString();
                movie.setTitle(title.getText().toString());
                if (sMovieid.equals("")) {
                    Toast.makeText(AdminEditMovie.this,
                            "Error in deleting a movie!",
                            Toast.LENGTH_LONG).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditMovie.this);
                    builder.setTitle("Proceed to delete " + sTitle + " from movie list?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new FirebaseDatabaseHelper().deleteMovi(test, new FirebaseDatabaseHelper.DataStatus() {
                                @Override
                                public void dataIsLoad(List<Movie> movie, List<String> keys) {}

                                @Override
                                public void dataIsUpdate() {}

                                @Override
                                public void dataIsDeleted() {
                                    Toast.makeText(AdminEditMovie.this,
                                            "A movie, " + sTitle + ", has been deleted successfully.",
                                            Toast.LENGTH_LONG).show();
                                    Intent list = new Intent(AdminEditMovie.this, AdminMovieList.class);
                                    startActivity(list);
                                }
                            });
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
}