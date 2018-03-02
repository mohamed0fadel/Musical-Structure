package com.example.android.musicstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class MediaDetailsActivity extends AppCompatActivity {

    private Song currentlyPlayingSong;
    private ArrayList<Song> songsArrayList;
    private ImageView artistImageView;
    private ImageButton playButton;
    private ImageButton previousButton;
    private ImageButton nextButton;
    private ImageButton backButton;
    private int currentSongIndex;
    private boolean playing = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);

        artistImageView = findViewById(R.id.det_img_artist_image);
        playButton = findViewById(R.id.btn_play);
        previousButton = findViewById(R.id.btn_previous);
        nextButton = findViewById(R.id.btn_next);
        backButton = findViewById(R.id.btn_home);

        currentlyPlayingSong = (Song)getIntent().getSerializableExtra("currentSong");
        songsArrayList = (ArrayList<Song>) getIntent().getSerializableExtra("songsArrayList");
        currentSongIndex = findIndex(songsArrayList);
        updatePlayIcon();

        artistImageView.setImageResource(currentlyPlayingSong.getImage());
        getSupportActionBar().setTitle(currentlyPlayingSong.getName());

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayIcon();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveNext();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePrevious();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * moves to the previous song in the list and updates the views
     */
    private void movePrevious(){
        if(currentSongIndex <= 0)
            currentSongIndex = songsArrayList.size()-1;
        else
            currentSongIndex--;
        artistImageView.setImageResource(songsArrayList.get(currentSongIndex).getImage());
        getSupportActionBar().setTitle(songsArrayList.get(currentSongIndex).getName());
        playing = true;
    }

    /**
     * moves to the next song in the list and updates the views
     */
    private void moveNext(){
        if(currentSongIndex >= songsArrayList.size()-1)
            currentSongIndex = 0;
        else
            currentSongIndex++;
        artistImageView.setImageResource(songsArrayList.get(currentSongIndex).getImage());
        getSupportActionBar().setTitle(songsArrayList.get(currentSongIndex).getName());
        playing = true;
    }

    /**
     * updates the play button
     */
    private void updatePlayIcon(){
        ImageButton imageButton = findViewById(R.id.btn_play);
        if(playing){
            imageButton.setImageResource(android.R.drawable.ic_media_pause);
            playing = false;
        }
        else{
            imageButton.setImageResource(android.R.drawable.ic_media_play);
            playing = true;
        }
    }

    /**
     * finds the index of the currently playing song in the songs arrayList
     * @param arrayList the list of the songs
     * @return the index of the currently playing song
     */
    private int findIndex(ArrayList<Song> arrayList){
        int index = 0;
        for(int i = 0; i < arrayList.size(); i++){
            if(currentlyPlayingSong.getId() == arrayList.get(i).getId())
                index = i;
        }
        return index;
    }

}
