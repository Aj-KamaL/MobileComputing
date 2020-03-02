package com.example.ajkamal.mplay;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class PowerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED))
        {
            Toast.makeText(context,"Power Connected",Toast.LENGTH_LONG).show();
            Log.i("Power Connected", "Received broadcast intent: " + intent.getAction());
        }
        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED))
        {
            Toast.makeText(context,"Power Disconnected",Toast.LENGTH_LONG).show();
            Log.i("Power Disconnected", "Received broadcast intent: " + intent.getAction());
        }




    }
}
