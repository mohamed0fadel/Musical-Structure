package com.example.android.musicstructure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MohamedFadel on 1/19/2018.
 */

public class CustomAdapter extends ArrayAdapter<Song> {

    public CustomAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Song currentSong = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.txt_song_name);
        miwokTextView.setText(currentSong.getName());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.txt_artist_name);
        defaultTextView.setText(currentSong.getArtist());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(currentSong.getImage());

        return listItemView;
    }
}
