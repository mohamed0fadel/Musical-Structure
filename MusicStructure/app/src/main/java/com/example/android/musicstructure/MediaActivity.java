package com.example.android.musicstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MediaActivity extends AppCompatActivity {

    private ArrayList<Song> songArrayList;
    private ArrayList<Song> songsToPlay;
    private ArrayList<Album> albumArrayList;
    private ListView songsListView;
    private ImageView artistImageView;
    private ImageButton playImageButton;
    private CustomAdapter customAdapter;
    private AlbumAdapter albumAdapter;
    private boolean playing;
    private boolean isAlbum;
    private boolean isPlaylist;
    private int currentlyPlayingSong = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        songsListView = findViewById(R.id.list_view);
        artistImageView = findViewById(R.id.txt_player_artist_image);
        playImageButton = findViewById(R.id.img_play_icon);
        songArrayList = fillAllSongsList();
        fillDataToPlay();
        if(isAlbum){
            albumAdapter = new AlbumAdapter(MediaActivity.this, albumArrayList);
            songsListView.setAdapter(albumAdapter);
            artistImageView.setImageResource(albumArrayList.get(0).getImage());
            albumsListingItemOne();
        }else{
            initializeCurrentSong();
            customAdapter = new CustomAdapter(MediaActivity.this, songsToPlay);
            songsListView.setAdapter(customAdapter);
        }
        songsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaControler(i);
            }
        });

        artistImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSongDetails();
            }
        });

        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayIcon();
            }
        });
    }

    /**
     * used to find out what to display either list of songs or a list of albums
     * @param position the current song index or the current album index if album is displayed
     */
    private void mediaControler(int position){
        if(isAlbum){
            songsToPlay = albumArrayList.get(position).getSongs();
            isAlbum = false;
            initializeCurrentSong();
            customAdapter = new CustomAdapter(MediaActivity.this, songsToPlay);
            songsListView.setAdapter(customAdapter);
        }else{
            if(currentlyPlayingSong != songsToPlay.get(position).getId())
                playing = true;
            play(position);
            updatePlayIcon();
        }
    }

    /**
     * updates the views when song is selected
     * @param position the currently plying song
     */
    private void play(int position){
        ImageView imageView = findViewById(R.id.txt_player_artist_image);
        TextView songName = findViewById(R.id.txt_player_song_name);
        TextView artistName = findViewById(R.id.txt_player_artist_name);
        ImageButton imageButton = findViewById(R.id.img_play_icon);
        Song currentSong = songsToPlay.get(position);
        currentlyPlayingSong = position;
        imageView.setImageResource(currentSong.getImage());
        songName.setText(currentSong.getName());
        artistName.setText(currentSong.getArtist());
        imageButton.setImageResource(android.R.drawable.ic_media_play);
    }

    /**
     * updates the play button
     */
    private void updatePlayIcon(){
            ImageButton imageButton = findViewById(R.id.img_play_icon);
            if(!isAlbum){
                if (playing) {
                    imageButton.setImageResource(android.R.drawable.ic_media_pause);
                    playing = false;
                } else {
                    imageButton.setImageResource(android.R.drawable.ic_media_play);
                    playing = true;
                }
            }
    }

    /**
     * sets the views to display the first song of a list
     */
    private void initializeCurrentSong(){
        play(0);
        playing = false;
        updatePlayIcon();
    }

    /**
     * updates the views to diplay the details of the first album in the albums list
     */
    private void albumsListingItemOne(){
        ImageView imageView = findViewById(R.id.txt_player_artist_image);
        TextView albumName = findViewById(R.id.txt_player_song_name);
        TextView artistName = findViewById(R.id.txt_player_artist_name);
        imageView.setImageResource(albumArrayList.get(0).getImage());
        albumName.setText(albumArrayList.get(0).getName());
        artistName.setText(albumArrayList.get(0).getArtist());
    }

    /**
     * select the data to display according to the user choice
     */
    private void fillDataToPlay(){
        int type = getIntent().getIntExtra("type",2);
        switch (type){
            case 1:
                songsToPlay = getRecentlyPlayedSongs();
                getSupportActionBar().setTitle(getResources().getString(R.string.recently_played));
                break;
            case 3:
                songsToPlay = getFavoriteSongs();
                getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
                break;
            case 4:
                 albumArrayList = getAlbums();
                 getSupportActionBar().setTitle(getResources().getString(R.string.album));
                 isAlbum = true;
                break;
           default:
               songsToPlay = fillAllSongsList();
               getSupportActionBar().setTitle(getResources().getString(R.string.songs));
        }
    }

    /**
     * moves to the MediaDetailsActivity carrying the the current song a list of the songs
     */
    public void showSongDetails() {
        if(isAlbum){
            songsToPlay = albumArrayList.get(0).getSongs();
            isAlbum = false;
            initializeCurrentSong();
            customAdapter = new CustomAdapter(MediaActivity.this, songsToPlay);
            songsListView.setAdapter(customAdapter);
        }else {
            Intent intent = new Intent(this, MediaDetailsActivity.class);
            Song song = songsToPlay.get(currentlyPlayingSong);
            intent.putExtra("currentSong",song);
            intent.putExtra("songsArrayList", songsToPlay);
            startActivity(intent);
        }
    }

    /**
     * fills the songsArrayList with all the songs
     * @return ArrayList of all the Songs
     */
    private ArrayList<Song> fillAllSongsList(){
        ArrayList<Song> arrayList = new ArrayList<>();
        arrayList.add(new Song(0, "Tell Me You Love Me",getResources().getString(R.string.demi), false, false, R.drawable.demi));
        arrayList.add(new Song(1, "Weak",getResources().getString(R.string.ajr), true, false, R.drawable.ajr));
        arrayList.add(new Song(2, "New",getResources().getString(R.string.daya), false, true, R.drawable.daya));
        arrayList.add(new Song(3, "Perfect",getResources().getString(R.string.ed), false, true, R.drawable.ed));
        arrayList.add(new Song(4, "Him & I",getResources().getString(R.string.halsey), false, false, R.drawable.halsey));
        arrayList.add(new Song(5, "Mirror Mirror",getResources().getString(R.string.marina), true, false, R.drawable.marina));
        arrayList.add(new Song(6, "Freez You Out",getResources().getString(R.string.marina), false, false, R.drawable.marina));
        arrayList.add(new Song(7, "Pocketful Of Sunshine",getResources().getString(R.string.natasha), true, false, R.drawable.natasha));
        arrayList.add(new Song(8, "Wolves",getResources().getString(R.string.selena), true, true, R.drawable.selena));
        arrayList.add(new Song(9, "I Don't Know Why",getResources().getString(R.string.imagine), false, true, R.drawable.evolve));
        arrayList.add(new Song(10, "Whatever It Takes",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(11, "Believer",getResources().getString(R.string.imagine), true, false, R.drawable.evolve));
        arrayList.add(new Song(12, "Walking The Wire",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(13, "Rise Up",getResources().getString(R.string.imagine), true, true, R.drawable.evolve));
        arrayList.add(new Song(14, "I Will Make It Up To You",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(15, "Yesterday",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(16, "Mouth Of The River",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(17, "Thunder",getResources().getString(R.string.imagine), true, false, R.drawable.evolve));
        arrayList.add(new Song(18, "Start Over",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(19, "Dancing In The Dark",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(new Song(20, "Santa's Coming For Us",getResources().getString(R.string.sia), true, true, R.drawable.sia));
        arrayList.add(new Song(21, "Candy Cane Lane",getResources().getString(R.string.sia), true, true, R.drawable.sia));
        arrayList.add(new Song(22, "Snowman",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        arrayList.add(new Song(23, "Snowflake",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        arrayList.add(new Song(24, "HO HO HO",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        arrayList.add(new Song(25, "Puppies Are Forever",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        arrayList.add(new Song(26, "Sunshine",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        arrayList.add(new Song(20, "Everyday Is Christmas",getResources().getString(R.string.sia), true, true, R.drawable.sia));
        return arrayList;
    }

    /**
     * fills a list with the albums
     * @return ArrayList of all the albums
     */
    private ArrayList<Album> getAlbums(){
        ArrayList<Album> arrayList = new ArrayList<>();
        Album album = new Album(getResources().getString(R.string.evolve), 0);
        album.addSong(new Song(0, "I Don't Know Why",getResources().getString(R.string.imagine), false, true, R.drawable.evolve));
        album.addSong(new Song(1, "Whatever It Takes",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        album.addSong(new Song(2, "Believer",getResources().getString(R.string.imagine), true, false, R.drawable.evolve));
        album.addSong(new Song(3, "Walking The Wire",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        album.addSong(new Song(4, "Rise Up",getResources().getString(R.string.imagine), true, true, R.drawable.evolve));
        album.addSong(new Song(5, "I Will Make It Up To You",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        album.addSong(new Song(6, "Yesterday",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        album.addSong(new Song(7, "Mouth Of The River",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        album.addSong(new Song(8, "Thunder",getResources().getString(R.string.imagine), true, false, R.drawable.evolve));
        album.addSong(new Song(9, "Start Over",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        album.addSong(new Song(10, "Dancing In The Dark",getResources().getString(R.string.imagine), false, false, R.drawable.evolve));
        arrayList.add(album);
        Album album2 = new Album(getResources().getString(R.string.christmas), 1);
        album2.addSong(new Song(0, "Santa's Coming For Us", getResources().getString(R.string.sia), true, true, R.drawable.sia));
        album2.addSong(new Song(1, "Candy Cane Lane",getResources().getString(R.string.sia), true, true, R.drawable.sia));
        album2.addSong(new Song(2, "Snowman",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        album2.addSong(new Song(3, "Snowflake",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        album2.addSong(new Song(4, "HO HO HO",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        album2.addSong(new Song(5, "Puppies Are Forever",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        album2.addSong(new Song(6, "Sunshine",getResources().getString(R.string.sia), false, true, R.drawable.sia));
        album2.addSong(new Song(7, "Everyday Is Christmas",getResources().getString(R.string.sia), true, true, R.drawable.sia));
        arrayList.add(album2);
        return arrayList;
    }

    /**
     * fills a list with the user favorite songs
     * @return ArrayList of all the favorite songs
     */
    private ArrayList<Song> getFavoriteSongs(){
        ArrayList<Song> arrayList = new ArrayList<>();
        for(Song song : songArrayList){
            if(song.isFavorite())
                arrayList.add(song);
        }
        return arrayList;
    }

    /**
     * fills a list with the recently played songs
     * @return ArrayList of all the recently played songs
     */
    private ArrayList<Song> getRecentlyPlayedSongs(){
        ArrayList<Song> arrayList = new ArrayList<>();
        for(Song song : songArrayList){
            if(song.isRecentlyPlayed())
                arrayList.add(song);
        }
        return arrayList;
    }

}
