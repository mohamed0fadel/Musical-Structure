package com.example.android.musicstructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MohamedFadel on 1/20/2018.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {
    public AlbumAdapter(Context context, ArrayList<Album> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Album currentAlbum = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.txt_song_name);
        miwokTextView.setText(currentAlbum.getName());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.txt_artist_name);
        defaultTextView.setText(currentAlbum.getArtist());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentAlbum.getImage());

        return listItemView;
    }
}
