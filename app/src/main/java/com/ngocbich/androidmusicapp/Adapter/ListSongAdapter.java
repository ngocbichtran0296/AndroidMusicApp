package com.ngocbich.androidmusicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngocbich.androidmusicapp.MainActivity;
import com.ngocbich.androidmusicapp.Model.Artist;
import com.ngocbich.androidmusicapp.Model.Song;
import com.ngocbich.androidmusicapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class ListSongAdapter extends ArrayAdapter<Song> {
    private Context context;
    private int resource;
    private List<Song> songs;

    public ListSongAdapter(Context context, int resource, List<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.resource = resource;
        this.songs = songs;
        Log.d("songname", "khoi tao");
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("song", "khoi tao");
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_list_song, null);
        }

        Song song=songs.get(position);

        TextView tvName = convertView.findViewById(R.id.song_name);
        tvName.setText(songs.get(position).getSongName() + "");

        TextView tvSinger = convertView.findViewById(R.id.song_artist);
        for(Artist artist: MainActivity.artists){
            if(artist.getArtistId().equals(song.getSongArtist())){
                tvSinger.setText(artist.getArtistName()+"");
                Log.d("nameArtist",artist.getArtistName());
            }
        }




        return convertView;
    }
}
