package com.example.android.musicstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView recentlyPlayed;
    private TextView allSongs;
    private TextView favorite;
    private TextView albume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recentlyPlayed = findViewById(R.id.txt_recently_played);
        allSongs = findViewById(R.id.txt_all_songs);
        favorite = findViewById(R.id.txt_favorite);
        albume = findViewById(R.id.txt_albume);

        // intent to move to the next activity with the type of data to display(songs or albums)
        final Intent intent = new Intent(MainActivity.this, MediaActivity.class);

        recentlyPlayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

        allSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });


        albume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("type", 4);
                startActivity(intent);
            }
        });
    }

}
