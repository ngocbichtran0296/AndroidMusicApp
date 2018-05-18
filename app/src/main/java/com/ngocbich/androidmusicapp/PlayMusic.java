package com.ngocbich.androidmusicapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ngocbich.androidmusicapp.Model.Artist;
import com.ngocbich.androidmusicapp.Model.Song;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayMusic extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_nameSong;
    private TextView tv_nameSinger;
    private TextView tv_timeStart;
    private TextView tv_timeEnd;

    private SeekBar seekBar;

    private ImageButton img_previous;
    private ImageButton img_next;
    private ImageButton img_play;

    private ImageView imageView;
    private ImageView add_to_playlist;

    private int position;
    private String songName;
    private String singerName;


    private MediaPlayer mediaPlayer;

    private Animation animation;

    public static String path = "https://docs.google.com/uc?export=download&id=";

    public static final String ACTION_PLAY = "action_play";
    private Intent playIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        init();

        Intent intent=getIntent();
        position=intent.getIntExtra(Home.INDEX,Home.POSITION);
        initMediaPlayer();
        mediaPlayer.start();
        img_play.setImageResource(R.drawable.pause);

        img_play.setOnClickListener(this);
        img_next.setOnClickListener(this);
        img_previous.setOnClickListener(this);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }


    private void init(){
        tv_nameSong= findViewById(R.id.name_song);
        tv_nameSinger=findViewById(R.id.name_singer);
        tv_timeStart=findViewById(R.id.tv_timeEnd);
        tv_timeEnd=findViewById(R.id.tv_timeStart);

        seekBar = findViewById(R.id.seek_bar);

        img_previous=findViewById(R.id.btnPrevious);
        img_next=findViewById(R.id.btnNext);
        img_play=findViewById(R.id.btnPlay);

        imageView=findViewById(R.id.anim);

    }

    private void initMediaPlayer(){
        songName=MainActivity.songs.get(position).getSongName();
        for(Artist artist:MainActivity.artists){
            if(artist.getArtistId().equals(MainActivity.songs.get(position).getSongArtist())){
                singerName=artist.getArtistName();
            }
        }

        Log.d("INDEX",position+"");
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path+MainActivity.songs.get(position).getSongLink());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        tv_nameSong.setText(songName);
        tv_nameSinger.setText(singerName);
        setTimeSong();
        updateTimeSong();
        animation= AnimationUtils.loadAnimation(PlayMusic.this,R.anim.infinite);

    }


    private void setTimeSong(){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("mm:ss");
        int time=mediaPlayer.getDuration();
        tv_timeEnd.setText(simpleDateFormat.format(time));
        //Gán time cho maxSeek
        seekBar.setMax(time);
    }

    private void updateTimeSong(){
        final Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("mm:ss");
                tv_timeStart.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));

                handler.postDelayed(this,500);
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                //chuyển next bài hát
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position>MainActivity.songs.size()-1){
                            position=0;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        initMediaPlayer();
                        mediaPlayer.start();
                        img_play.setImageResource(R.drawable.pause);
                    }
                });
            }
        },100);

    }

    public void songPicked() {
        Intent intent = new Intent(ACTION_PLAY);
        intent.putExtra(Home.INDEX, Home.POSITION);
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPrevious:
                position--;
                Log.d("pre",position+"");
                if(position<0){
                    position=MainActivity.songs.size()-1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                initMediaPlayer();
                mediaPlayer.start();
                img_play.setImageResource(R.drawable.pause);
                imageView.startAnimation(animation);
                break;
            case R.id.btnNext:
                position++;
                Log.d("next",position+"");
                if (position>MainActivity.songs.size()-1){
                    position=0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                initMediaPlayer();
                mediaPlayer.start();
                img_play.setImageResource(R.drawable.pause);
                imageView.startAnimation(animation);
                break;
            case R.id.btnPlay:
                Log.d("INDEX",position+"");
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    img_play.setImageResource(R.drawable.play);
                    imageView.clearAnimation();
                }else {
                    mediaPlayer.start();
                    img_play.setImageResource(R.drawable.pause);
                    imageView.startAnimation(animation);
                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();

        }

        mediaPlayer.release();

       // handler.removeCallbacks(updater);
        //   seekBar.setProgress(0);


        super.onBackPressed();
    }
}
