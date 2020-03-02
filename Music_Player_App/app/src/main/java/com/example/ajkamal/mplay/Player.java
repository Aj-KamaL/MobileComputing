package com.example.ajkamal.mplay;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
public class Player extends Service {

    public MediaPlayer mPlayer;
    public void play(String song,Context c) {
        stopp();
        if(song.equals("sample")) {
            mPlayer = MediaPlayer.create(c, R.raw.sample);
        }
        else if (song.equals("sample2")){
            mPlayer = MediaPlayer.create(c, R.raw.sample2);
        }
        else{
            mPlayer = MediaPlayer.create(c, Uri.parse(c.getFilesDir()+"/"+"sample3.mp3"));
        }
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            public void onCompletion(MediaPlayer mp) {
                                                stopp();
                                            }
                                        }
        );
        mPlayer.start();
    }
    public void stopp() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
    public boolean search(Context c){
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            connected = true;
        }
        else {
            connected = false;
        }
        return connected;

    }
    public void DownloadFiles(Context c,String url){
        try
        {
            URL u = new URL(url);
            InputStream is = u.openStream();
            DataInputStream dis = new DataInputStream(is);
            byte[] buffer = new byte[1024];
            int length;
            File src=new File(c.getFilesDir(),"sample3.mp3");
            FileOutputStream fos = new FileOutputStream(src);
            while ((length = dis.read(buffer))>0) {
                fos.write(buffer, 0, length);
            }
        }
        catch (MalformedURLException mue)
        {
            Log.e("syn", "urlerror", mue);
        }
        catch (IOException ioe)
        {
            Log.e("syn", "ioerror", ioe);
        }
        catch (SecurityException se)
        {
            Log.e("syn", "securityerror", se);
        }


    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);
        Log.i("dd","after"+BlankFragment.posi);
        int position=BlankFragment.posi;
        stopp();
        if(position==0) {
            mPlayer = MediaPlayer.create(this, R.raw.sample);
        }
        else if (position==1){
            mPlayer = MediaPlayer.create(this, R.raw.sample2);
        }
        else{
            mPlayer = MediaPlayer.create(this, Uri.parse(this.getFilesDir()+"/"+"sample3.mp3"));
        }
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            public void onCompletion(MediaPlayer mp) {
                                                stopp();
                                            }
                                        }
        );
        mPlayer.start();
        return START_STICKY;
    }



    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }



}
