package com.example.ajkamal.mplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aj Kamal on 03-09-2018.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Boot Complete",Toast.LENGTH_LONG).show();

        Log.i("BootUp", "Received broadcast intent: " + intent.getAction());
    }
}
