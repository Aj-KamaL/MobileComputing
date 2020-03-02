package com.example.ajkamal.quizapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contair, new queslist()).commit();
        }
        Log.e("Activity","tree 1");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isChangingConfigurations()){
            Log.e("TAG2","rotated");

        }
        else{
            Log.e("TAG2","closed");
            SQLiteOpenHelper mdb=new Data(this);
            this.deleteDatabase(((Data)mdb).DATABASE_NAME);
//
//            SQLiteDatabase db=mdb.getWritableDatabase();
//            db.execSQL("delete from "+((Data)mdb).TABLE_NAME);



        }
    }
}
