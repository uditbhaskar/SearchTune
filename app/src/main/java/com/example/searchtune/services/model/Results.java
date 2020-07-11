package com.example.searchtune.services.model;


public class Results {


    private String artistName;

    private String trackName;

    private String artworkUrl100;

    private double trackPrice;


    private String primaryGenreName;


    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackName() {
        return this.trackName;
    }


    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getArtworkUrl100() {
        return this.artworkUrl100;
    }


    public void setTrackPrice(double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public double getTrackPrice() {
        return this.trackPrice;
    }


    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getPrimaryGenreName() {
        return this.primaryGenreName;
    }


}

