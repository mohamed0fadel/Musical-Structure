package com.example.android.musicstructure;

import java.util.ArrayList;

/**
 * Created by MohamedFadel on 1/19/2018.
 */

public class Album {
    private String name;
    private int id;
    private int image;
    private String artist;
    private ArrayList<Song> songs = new ArrayList<>();

    public Album(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * adds a new song to the albums and sets the album art from the added songs
     * @param song song to be added
     */
    public void addSong(Song song) {
        this.songs.add(song);
        image = song.getImage();
        artist = song.getArtist();
    }
}
