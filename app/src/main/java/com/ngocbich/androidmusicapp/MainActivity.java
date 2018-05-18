package com.ngocbich.androidmusicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ngocbich.androidmusicapp.Model.Account;
import com.ngocbich.androidmusicapp.Model.Artist;
import com.ngocbich.androidmusicapp.Model.Genre;
import com.ngocbich.androidmusicapp.Model.PlayList;
import com.ngocbich.androidmusicapp.Model.Song;
import com.ngocbich.androidmusicapp.Model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btSignIn,btSignUp;
    TextView slogan;

    public static List<Song> songs=new ArrayList<>();
    public static List<Account> accounts=new ArrayList<>();
    public static List<Artist> artists=new ArrayList<>();
    public static List<Genre> genres=new ArrayList<>();
    public static List<PlayList> playlist=new ArrayList<>();
    public static List<String> yourplaylist=new ArrayList<>();//chua id song


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSignIn=findViewById(R.id.btSignIn);
        btSignUp=findViewById(R.id.btSignUp);

        slogan=findViewById(R.id.slogan);

        new GetSong().execute("http://192.168.1.20:34760/api/Songs");
        new GetArtist().execute("http://192.168.1.20:34760/api/Artists");
        new GetPlayList().execute("http://192.168.1.20:34760/api/Playlists");


        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
            }
        });
    }

    class GetSong extends AsyncTask<String,Void,Integer>{

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }

        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpsURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpsURLConnection=(HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpsURLConnection.connect();
                Log.d("url",urlString);
                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpsURLConnection.getInputStream();
                Log.d("input",inputStream.toString());
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    songs.add(new Song(jsonObject.getString("SongId"),jsonObject.getString("SongName"),
                            jsonObject.getString("SongLink"),jsonObject.getString("SongGenre"),
                            jsonObject.getString("SongArtist"),jsonObject.getString("SongRating")));
                }
                for (Song song:songs){
                    Log.d("AAA",song.toString());
                }
            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }


    class GetUser extends  AsyncTask<String,Void,Integer>{

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpsURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpsURLConnection=(HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpsURLConnection.connect();
                Log.d("url",urlString);
                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpsURLConnection.getInputStream();
                Log.d("input",inputStream.toString());
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    accounts.add(new Account(jsonObject.getString("UserId"),jsonObject.getString("UserName"),
                            jsonObject.getString("UserPassword")));
                }
                for (Account user:accounts){
                    Log.d("AAA",user.toString());
                }
            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }


    class GetArtist extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpsURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpsURLConnection=(HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpsURLConnection.connect();
                Log.d("url",urlString);
                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpsURLConnection.getInputStream();
                Log.d("input",inputStream.toString());
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    artists.add(new Artist(jsonObject.getString("ArtistId"),jsonObject.getString("ArtistName")));
                }
                for (Artist artist:artists){
                    Log.d("AAA",artist.toString());
                }
            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }

    class GetGenre extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpsURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpsURLConnection=(HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpsURLConnection.connect();
                Log.d("url",urlString);
                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpsURLConnection.getInputStream();
                Log.d("input",inputStream.toString());
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    genres.add(new Genre(jsonObject.getString("GenreId"),jsonObject.getString("GenreName")));
                }
                for (Genre genre:genres){
                    Log.d("AAA",genre.toString());
                }
            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }

    class GetPlayList extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpsURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpsURLConnection=(HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpsURLConnection.connect();
                Log.d("url",urlString);
                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpsURLConnection.getInputStream();
                Log.d("input",inputStream.toString());
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    playlist.add(new PlayList(jsonObject.getString("PlayListId"),jsonObject.getString("UserName"),jsonObject.getString("SongId")));
                }
                for (PlayList playlist:playlist){
                    Log.d("AAA",playlist.toString());
                }
            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }
}
