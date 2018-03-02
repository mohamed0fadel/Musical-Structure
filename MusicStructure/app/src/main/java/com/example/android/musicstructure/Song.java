package com.example.android.musicstructure;

import java.io.Serializable;

/**
 * Created by MohamedFadel on 1/19/2018.
 */

public class Song implements Serializable{
    private String name;
    private String artist;
    private boolean recentlyPlayed;
    private boolean favorite;
    private int id;
    private int image;
    public static int currentlyPlaying;

    public Song(int id, String name, String artist, boolean recentlyPlayed, boolean favorite, int image) {
        this.name = name;
        this.artist = artist;
        this.recentlyPlayed = recentlyPlayed;
        this.favorite = favorite;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isRecentlyPlayed() {
        return recentlyPlayed;
    }

    public void setRecentlyPlayed(boolean recentlyPlayed) {
        this.recentlyPlayed = recentlyPlayed;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
