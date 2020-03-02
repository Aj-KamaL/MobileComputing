package com.example.ajkamal.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
public class Score extends AsyncTask<String,Integer,Boolean> {
    Context c;
    ProgressBar sc;
    SQLiteOpenHelper dbhelper;
    int ans=0;

    public Score(Context c, SQLiteOpenHelper f, ProgressBar sc) {
        this.c = c;
        this.sc = sc;
        dbhelper = (Data) f;
    }
    @Override
    protected void onPreExecute()
    {
    }
    @Override
    protected Boolean doInBackground(String... strings) {
        this.c = c;
        //dialog= new ProgressDialog(c);
        Cursor curCSV = ((Data) dbhelper).raw();
        int idx = 0;
        while (curCSV.moveToNext()&& idx < 30 ) {
            if (curCSV.getString(3).equals(curCSV.getString(4))){
                ans++;
            }
            publishProgress(ans);
            idx++;
        }

        curCSV.close();
        dbhelper.close();
        return true;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.e("Score",values[0]+" ");
        sc.setProgress(values[0]);
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        Toast.makeText(c, "Your Score is "+ans+" / 30", Toast.LENGTH_SHORT).show();

    }
}