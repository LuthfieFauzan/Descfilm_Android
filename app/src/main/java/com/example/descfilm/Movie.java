package com.example.descfilm;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title, genre, language, runtime, producer, director, distributor, year, rating,
            casts, synopsisshort, synopsisline1, synopsisline2, videourl, image;

    public Movie() {}

    public Movie(String title, String genre, String language, String runtime, String producer,
                 String director, String distributor, String year, String rating, String casts,
                 String synopsisshort, String synopsisline1, String synopsisline2, String videourl,
                 String image) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.runtime = runtime;
        this.producer = producer;
        this.director = director;
        this.distributor = distributor;
        this.year = year;
        this.rating = rating;
        this.casts = casts;
        this.synopsisshort = synopsisshort;
        this.synopsisline1 = synopsisline1;
        this.synopsisline2 = synopsisline2;
        this.videourl = videourl;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCasts() {
        return casts;
    }
    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getSynopsisshort() {
        return synopsisshort;
    }
    public void setSynopsisshort(String synopsisshort) {
        this.synopsisshort = synopsisshort;
    }

    public String getSynopsisline1() {
        return synopsisline1;
    }
    public void setSynopsisline1(String synopsisline1) {
        this.synopsisline1 = synopsisline1;
    }

    public String getSynopsisline2() {
        return synopsisline2;
    }
    public void setSynopsisline2(String synopsisline2) {
        this.synopsisline2 = synopsisline2;
    }

    public String getVideourl() {
        return videourl;
    }
    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}