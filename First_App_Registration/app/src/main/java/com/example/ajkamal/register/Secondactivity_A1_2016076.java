package com.example.ajkamal.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Secondactivity_A1_2016076 extends AppCompatActivity {
    String currnt="";
    String mssg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        ((TextView) findViewById(R.id.textView)).setText(name);

        String roll = intent.getExtras().getString("Roll");
        ((TextView) findViewById(R.id.textView2)).setText(roll);

        String branch = intent.getExtras().getString("Branch");
        ((TextView) findViewById(R.id.textView3)).setText(branch);

        String c1 = intent.getExtras().getString("C1");
        ((TextView) findViewById(R.id.textView5)).setText(c1);

        String c2 = intent.getExtras().getString("C2");
        ((TextView) findViewById(R.id.textView6)).setText(c2);

        String c3 = intent.getExtras().getString("C3");
        ((TextView) findViewById(R.id.textView7)).setText(c3);

        String c4 = intent.getExtras().getString("C4");
        ((TextView) findViewById(R.id.textView8)).setText(c4);
        mssg="State of SecondActivity changed to OnCreate";
        currnt="OnCreate";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(currnt.length()==0){
            mssg="State of SecondActivity changed to OnStart";
        }
        else{
            mssg="State of SecondActivity changed from " +currnt+" to OnStart";

        }
        currnt="OnStart";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        if(currnt.length()==0){
            mssg="State of SecondActivity changed to OnRestart";
        }
        else{
            mssg="State of SecondActivity changed from : " +currnt+" to OnRestart";

        }
        currnt="OnRestart";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(currnt.length()==0){
            mssg="State of SecondActivity changed to OnResume";
        }
        else{
            mssg="State of SecondActivity changed from : " +currnt+" to OnResume";

        }
        currnt="OnResume";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(currnt.length()==0){
            mssg="State of SecondActivity changed to OnDestroy";
        }
        else{
            mssg="State of SecondActivity changed from " +currnt+" to OnDestroy";

        }
        currnt="OnDestroy";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(currnt.length()==0){
            mssg="State of SecondActivity changed to OnStop";
        }
        else{
            mssg="State of SecondActivity changed from " +currnt+" to OnStop";

        }
        currnt="OnStop";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(currnt.length()==0){
            mssg="State of SecondActivity changed to OnPause";
        }
        else{
            mssg="State of SecondActivity changed from " +currnt+" to OnPause";
        }
        currnt="OnPause";
        Toast.makeText(Secondactivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
}
