package com.example.ajkamal.quizapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportDatabaseCSVTask extends AsyncTask<String,Integer, Boolean> {
    ProgressBar pg;
    ProgressBar sc;
    ProgressBar dd;
    SQLiteOpenHelper dbopn;
    Context c;
    File file=null;
    //View v;
    double val=0;
    //public ProgressDialog dialog=null;

    public ExportDatabaseCSVTask(Context c,SQLiteOpenHelper f,ProgressBar cd,ProgressBar bd,ProgressBar ssc) {
        this.c=c;
        //dialog= new ProgressDialog(c);
        pg=cd;
        dd=bd;
        this.sc=ssc;
        Log.e("point"," " +pg.toString());
        dbopn=(Data)f;
    }
    @Override
    protected void onPreExecute()
    {
    }
    @Override
    protected Boolean doInBackground(final String... args) {
        Log.e("TAG","export 0.1");
        File exportDir = new File(c.getFilesDir(), "/codesss/");

        Log.e("TAG","export 0.2");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
            Log.e("TAG","export 0.21");
        }
        Log.e("TAG","export 0.3");
        file = new File(exportDir, "Answer.csv");
        Log.e("TAG","export 0.4");
        CSVWriter cw=null;
        Cursor cv = ((Data) dbopn).raw();

        try {
            Log.e("TAG", "export 0.5");
            if (file.exists())
            {
                file.delete();
            }
            file.createNewFile();
            Log.e("TAG", "export 1");
            cw = new CSVWriter(new FileWriter(file));
            Log.e("TAG", "export 2");

            Log.e("TAG", "export 3");
            cw.writeNext(cv.getColumnNames());
            Log.e("TAG", "export 4");
            int idx = 0;
            while (cv.moveToNext()&&idx<30) {
                String arrStr[] = null;
                String[] mySecondStringArray = new String[cv.getColumnNames().length];
                Log.e("TAG", "export 5.1 " + cv.getColumnNames().toString());
                for (int i = 0; i < cv.getColumnNames().length; i++) {
                    mySecondStringArray[i] = cv.getString(i);
                }
                cw.writeNext(mySecondStringArray);
                idx++;
                int ttemp=((100*idx)/30);
                publishProgress(ttemp);
                Log.e("percent", "export 5.2 "+ttemp);
            }
            Log.e("TAG", "export 6");
            Log.e("TAG", "export 6.2");
            cw.close();
            cv.close();

            dbopn.close();
            return true;
        }
        catch (IOException e)
        {
            try {
                cw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            cv.close();
            dbopn.close();
            return false;
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pg.setProgress(values[0]);
    }

    public String mime(String path) {
        String ext=MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);

    }
    @Override
    protected void onPostExecute(final Boolean success)
    {
        Log.e("Prog","Prog4");
        Log.e("Prog","Prog6");
        if (success) {
            Toast.makeText(c, "Export successful!", Toast.LENGTH_SHORT).show();
            ConnectivityManager co_ma= (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ac_in = co_ma.getActiveNetworkInfo();
            if(co_ma.getActiveNetworkInfo() != null && ac_in.isConnected())
            {
                new Upload(c,file,dd,dbopn,sc).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                Toast.makeText(c, "Check Network Connection : Not Connected", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(c, "Export failed", Toast.LENGTH_SHORT).show();
        }
    }
}