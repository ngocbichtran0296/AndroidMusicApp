package com.ngocbich.androidmusicapp.LoadData;

import android.os.AsyncTask;
import android.util.Log;

import com.ngocbich.androidmusicapp.Model.Song;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class GetSongs  extends AsyncTask<String,Void,Integer> {
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
            List<Song> songs=new ArrayList<>();
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
