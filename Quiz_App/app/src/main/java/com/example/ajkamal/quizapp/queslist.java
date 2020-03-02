package com.example.ajkamal.quizapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ProgressBar;


public class queslist extends Fragment {
    SQLiteOpenHelper mdb;
    Button sbmt;
    public ProgressBar progressBar;
    public ProgressBar progressBar2;
    public ProgressBar progressBar3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v= inflater.inflate(R.layout.fragment_queslist, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar2 = (ProgressBar) v.findViewById(R.id.progressBar2);
        progressBar2.setMax(100);
        progressBar3 = (ProgressBar) v.findViewById(R.id.progressBar3);
        progressBar3.setMax(30);

        RecyclerView pl=(RecyclerView)v.findViewById(R.id.rv2);
        pl.setLayoutManager(new LinearLayoutManager(getContext()));
        mdb=new Data(getContext());
        Log.e("In Frag","tree 2");

        if(((Data)mdb).getProfilesCount() == 0)
        {
            ((Data) mdb).insertData("QUES 1 COMPUTER LANGUAGE", "The Language that the computer can understand is called Machine Language.", "true", "NA");
            ((Data) mdb).insertData("QUES 2 E-MAIL", "You cannot format text in an e-mail message.", "false", "NA");
            ((Data) mdb).insertData("QUES 3 MAGNETIC TAPE", "Magnetic Tape used random access method.", "false", "NA");
            ((Data) mdb).insertData("QUES 4 PRINTER", "Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.", "true", "NA");
            ((Data) mdb).insertData("QUES 5 GNU", "GNU / Linux is a open source operating system.", "true", "NA");
            ((Data) mdb).insertData("QUES 6 FREEWARE", "Freeware is software that is available for use at no monetary cost.", "true", "NA");
            ((Data) mdb).insertData("QUES 7 IPv6 ", "IPv6 Internet Protocol address is represented as eight groups of four Octal digits.", "false", "NA");
            ((Data) mdb).insertData("QUES 8 HEXA-DECIMAL", "The hexadecimal number system contains digits from 1 - 15.", "false", "NA");
            ((Data) mdb).insertData("QUES 9 OCTAL", "Octal number system contains digits from 0 - 7.", "true", "NA");
            ((Data) mdb).insertData("QUES 10 MS WORD", "MS Word is a hardware.", "true", "NA");
            ((Data) mdb).insertData("QUES 11 MESSAGE", "You can only print one copy of a selected message.", "false", "NA");
            ((Data) mdb).insertData("QUES 12 CONTACT", "You cannot edit Contact forms.", "false", "NA");
            ((Data) mdb).insertData("QUES 13 E-MAILS", "You can delete e-mails from a Web-based e-mail account.", "true", "NA");
            ((Data) mdb).insertData("QUES 14 WEB-BASED", " Web-based e-mail accounts do not required passwords.", "false", "NA");
            ((Data) mdb).insertData("QUES 15 UNIQUE", "Your e-mail address must be unique.", "true", "NA");
            ((Data) mdb).insertData("QUES 16 SEND A FILE", "You cannot send a file from a Web-based e-mail account.", "false", "NA");
            ((Data) mdb).insertData("QUES 17 FOLDER", "There is only one way to create a new folder.", "false", "NA");
            ((Data) mdb).insertData("QUES 18 NEW FOLDERS", "New folders must all be at the same level.", "true", "NA");
            ((Data) mdb).insertData("QUES 19 OUTLOOK", " In Outlook you must store all of your messages in the Inbox.", "false", "NA");
            ((Data) mdb).insertData("QUES 20 ATTACHMENTS", "All attachment are safe.", "false", "NA");
            ((Data) mdb).insertData("QUES 21 ONE ATTACHMENT", "You can only send one attachment per e-mail message.", "false", "NA");
            ((Data) mdb).insertData("QUES 22 CPU", "CPU stands for Central Performance Unit.", "false", "NA");
            ((Data) mdb).insertData("QUES 23 INPUT DATA", "CPU controls only input data of computer.", "false", "NA");
            ((Data) mdb).insertData("QUES 24 PREVIEW", "You cannot preview a message before you print it.", "false", "NA");
            ((Data) mdb).insertData("QUES 25 CONTACT INFORMATION", "You need to delete a contact and creat a new one to change contact information.", "false", "NA");
            ((Data) mdb).insertData("QUES 26 SIGN-UP", "You can sign up for Web-based e-mail without accepting the Web site's terms and conditions. ", "false", "NA");
            ((Data) mdb).insertData("QUES 27 SNIPPET PASSWORD", "Your password should be something others will be able to figured out, such as your birthday or wedding anniversary.", "false", "NA");
            ((Data) mdb).insertData("QUES 28 DELETE BUTTON", "Pressing the Delete key and clicking the Delete button produce the same result.", "true", "NA");
            ((Data) mdb).insertData("QUES 29 SOLAR PLANET", "The earth is the third planet from the sun.", "true", "NA");
            ((Data) mdb).insertData("QUES 30 INTENTS", "There are two types of Intents", "true", "NA");
        }
        ques_item[] lii=new ques_item[30];
        Cursor res = ((Data) (mdb)).getAllData();
        if(res.getCount() == 0)
        {
            Log.e("tree"," NOTHING FOUND ");
        }
        else {
            StringBuffer buffer = new StringBuffer();
            int idx = 0;
            while (res.moveToNext()&& idx < 30 ) {

                Log.e("tree Priniting","Id :" + res.getString(0) + "\n");
                Log.e("tree Priniting","Name :" + res.getString(1) + "\n");
                Log.e("tree Priniting","Question :" + res.getString(2) + "\n");
                Log.e("tree Priniting","Solution :" + res.getString(3) + "\n");
                Log.e("tree Priniting","Answer :" + res.getString(4) + "\n");

                lii[idx++]=new ques_item(res.getString(1),res.getString(2),res.getString(3),res.getString(4) );
            }
        }


        // Show all data
//        showMessage("Data",buffer.toString());
        pl.setAdapter(new MyAdapter(lii,getContext()));
        Log.e("IN Frag"," tree 6 ");
        sbmt=(Button)v.findViewById(R.id.button4);

        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                progressBar2.setVisibility(View.VISIBLE);
                progressBar2.setProgress(0);
                progressBar3.setVisibility(View.VISIBLE);
                progressBar3.setProgress(0);



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                {
                    Log.e("TAG","export 0.01");
                    ExportDatabaseCSVTask rto=new ExportDatabaseCSVTask(getContext(),mdb,progressBar,progressBar2,progressBar3);
                    rto.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    Log.e("TAG","export 0.02");


                }
                else
                {
                    Log.e("TAG","export 0.02");

                    new ExportDatabaseCSVTask(getContext(),mdb,progressBar,progressBar2,progressBar3).execute();
                }

            }
        });


        mdb.close();

        return v;


    }

}
