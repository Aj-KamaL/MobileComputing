//Raj Kamal Yadav 2016076
package com.example.ajkamal.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.*;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import android.widget.Toast;

public class MainActivity_A1_2016076 extends AppCompatActivity implements OnClickListener{
    String currnt="";
    String mssg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.button);
        Button button2=(Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        mssg="State of MainActivity changed to OnCreate";
        currnt="OnCreate";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(currnt.length()==0){
            mssg="State of MainActivity changed to OnStart";
        }
        else{
            mssg="State of MainActivity changed from " +currnt+" to OnStart";

        }
        currnt="OnStart";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        if(currnt.length()==0){
            mssg="State of MainActivity changed to OnRestart";
        }
        else{
            mssg="State of MainActivity changed from "+currnt+" to OnRestart";

        }
        currnt="OnRestart";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(currnt.length()==0){
            mssg="State of MainActivity changed to OnResume";
        }
        else{
            mssg="State of MainActivity changed from " +currnt+" to OnResume";

        }
        currnt="OnResume";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(currnt.length()==0){
            mssg="State of MainActivity changed to OnDestroy";
        }
        else{
            mssg="State of MainActivity changed from " +currnt+" to OnDestroy";

        }
        currnt="OnDestroy";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(currnt.length()==0){
            mssg="State of MainActivity changed to OnStop";
        }
        else{
            mssg="State of MainActivity changed from " +currnt+" to OnStop";

        }
        currnt="OnStop";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(currnt.length()==0){
            mssg="State of MainActivity changed to OnPause";
        }
        else{
            mssg="State of MainActivity changed from " +currnt+" to OnPause";

        }
        currnt="OnPause";
        Toast.makeText(MainActivity_A1_2016076.this,mssg,Toast.LENGTH_SHORT).show();
        Log.i("",mssg);
    }
    @Override
    public void onClick(View V){
        if(V.getId()==R.id.button){
            String name = ((EditText) findViewById(R.id.editText)).getText().toString();
            String roll=((EditText) findViewById(R.id.editText2)).getText().toString();
            String branch=((EditText) findViewById(R.id.editText3)).getText().toString();
            String c1=((EditText) findViewById(R.id.editText4)).getText().toString();
            String c2=((EditText) findViewById(R.id.editText5)).getText().toString();
            String c3 =((EditText) findViewById(R.id.editText6)).getText().toString();
            String c4 =((EditText) findViewById(R.id.editText7)).getText().toString();
            Intent i_new =new Intent(this,Secondactivity_A1_2016076.class);
            i_new.putExtra("Name", name);
            i_new.putExtra("Roll",roll);
            i_new.putExtra("Branch",branch);
            i_new.putExtra("C1",c1);
            i_new.putExtra("C2",c2);
            i_new.putExtra("C3",c3);
            i_new.putExtra("C4",c4);
            startActivity(i_new);
        }
        if(V.getId()==R.id.button2){
            this.onDestroy();
            Intent i_new =new Intent(this,MainActivity_A1_2016076.class);
            startActivity(i_new);
        }

    }


}
