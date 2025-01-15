package com.example.descfilm;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ViewMovieDetail extends AppCompatActivity {
    TextView title, genre, year, runtime, synopsisshort, synopsisline1;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmoviedetail);

        final Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        title = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        year = findViewById(R.id.year);
        runtime = findViewById(R.id.runtime);
        synopsisshort = findViewById(R.id.synopsisshort);
        synopsisline1 = findViewById(R.id.synopsisline1);
        image = findViewById(R.id.imageView);

        String frameVideo =
                "<html>" +
                    "<body>" +
                        "<iframe width=\"345\" height=\"225\"" +
                            " src=\"" + movie.getVideourl() + "\" frameborder=\"0\" allowfullscreen>" +
                        "</iframe>" +
                    "</body>" +
                "</html>";

        WebView displayYoutubeVideo = findViewById(R.id.videourl);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");

        title.setText(movie.getTitle());
        genre.setText("Genre: " + movie.getGenre());
        year.setText("Year: " + movie.getYear());
        runtime.setText("Runtime: " + movie.getRuntime() + " minutes");
        synopsisshort.setText(movie.getSynopsisshort());
        synopsisline1.setText(movie.getSynopsisline1());

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/realtime-db-web-f1fff.appspot.com/o/img%2F"+movie.getImage()+"?alt=media")
                .into(image);
    }
}