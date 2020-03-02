package com.example.ajkamal.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Upload extends AsyncTask<String,Integer, Boolean> {
    File file;
    Context c;
    ProgressBar pg;
    ProgressBar sc;
    SQLiteOpenHelper dbopn;
    public Upload(Context c,File f,ProgressBar cd,SQLiteOpenHelper dbopnn,ProgressBar scc) {
        this.c=c;
        this.file=f;
        this.pg=cd;
        this.dbopn=dbopnn;
        this.sc=scc;
    }
    @Override
    protected void onPreExecute() {}

    protected Boolean doInBackground(final String... args) {
        Response response=null;
        RequestBody fb=null;
        RequestBody rb=null;
        Request req=null;
        try {
            Log.e("TAG", "export 6.3");

            String csvextension = getextension(file.getPath());
            Log.e("TAG", "export 7");
            String file_path = file.getAbsolutePath();
            Log.e("TAG", "export 8");
            Long time =System.currentTimeMillis();

            OkHttpClient socket = new OkHttpClient();
            Log.e("TAG", "export 9");
            fb= RequestBody.create(MediaType.parse(csvextension), file);
            Log.e("TAG", "export 10");

            rb = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("type", csvextension)
                    .addFormDataPart("upl_fil", file_path.substring(file_path.lastIndexOf("/") + 1), fb)
                    .build();

            Log.e("TAG", "export 11 "+file_path);

            req = new Request.Builder().url("http://192.168.49.203/save_file.php").post(rb).build();
            Log.e("TAG", "export 12");
            response = socket.newCall(req).execute();
            Log.e("TAG", "export 13");
            long time2=System.currentTimeMillis();
            int timediff=(int)(time2-time);
            Log.e("Time","Time "+timediff);
            for (int i=0;i<timediff;i++){
                int teem=((i*100)/timediff);
                publishProgress(teem);
                Thread.sleep(1);
                Log.e("percent", "export 5.2 "+teem );

            }
            publishProgress(100);


            if (!response.isSuccessful()) {
                Log.e("TAG", "export 14");
                return false;

            }
            return true;

        }
        catch (IOException e){
            try {
                Log.e("TAG", "export 12.2 "+rb.contentLength()+" "+fb.toString()+" "+req.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }


    }


    public String getextension(String path)
    {
        String ext=MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.e("percent"," "+values[0]);

        pg.setProgress(values[0]);

    }

    protected void onPostExecute(final Boolean success)
    {

        if (success) {
            new Score(c,dbopn,sc).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            Toast.makeText(c, "Upload successful!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(c, "Upload failed", Toast.LENGTH_SHORT).show();
        }
    }
}