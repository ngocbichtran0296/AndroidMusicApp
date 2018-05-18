package com.ngocbich.androidmusicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ngocbich.androidmusicapp.Adapter.ListSongAdapter;
import com.ngocbich.androidmusicapp.Common.Common;
import com.ngocbich.androidmusicapp.Model.Account;
import com.ngocbich.androidmusicapp.Model.PlayList;
import com.ngocbich.androidmusicapp.Model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView username;
    private ListView lvListSong;
    private ListSongAdapter songAdapter;

    public static String INDEX="INDEX";
    public static int POSITION=1;

    public static String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Addplaylist);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //set name for user
        View headerView=navigationView.getHeaderView(0);
        username=headerView.findViewById(R.id.userName);
        username.setText(Common.currentUser.getName());


        lvListSong=findViewById(R.id.lvListSong);

        songAdapter=new ListSongAdapter(Home.this,R.layout.row_list_song,MainActivity.songs);
        lvListSong.setAdapter(songAdapter);

        lvListSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Home.this,PlayMusic.class);
                intent.putExtra(INDEX,position);
                Log.d("INDEX",position+"");
                startActivity(intent);
            }
        });

        registerForContextMenu(lvListSong);

    }


    public void getYourplaylist(){
        String name=Common.currentUser.getName();

        for(Account acc: MainActivity.accounts){
            if(acc.getUserName().equals(name)){
                id=acc.getUserId();
                break;
            }
        }
        for(PlayList p:MainActivity.playlist){
            if(p.getUserId().equals(id)){
                MainActivity.yourplaylist.add(p.getSongId());
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_playlist) {
            songAdapter=new ListSongAdapter(Home.this,R.layout.row_list_song,MainActivity.songs);
            songAdapter.notifyDataSetChanged();
            lvListSong.setAdapter(songAdapter);
        } else if (id == R.id.nav_signout) {
            Intent signin=new Intent(Home.this,SignIn.class);
            signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signin);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();

    }
}
