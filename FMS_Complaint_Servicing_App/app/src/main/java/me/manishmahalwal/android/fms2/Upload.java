package me.manishmahalwal.android.fms2;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;



public class Upload extends AsyncTask<String,Integer, Boolean> {
    View v;
    Context c;
    final String CHANNEL_ID="personal_notifications";
    final int Notification=001;
    public Upload(View v,Context c) {
      this.v=v;
      this.c=c;
    }
    @Override
    protected void onPreExecute() {}

    protected Boolean doInBackground(final String... args)
    {
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Notification");
        int upd;
        myRef.addValueEventListener(new ValueEventListener(){
            int val;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                val = dataSnapshot.getValue(Integer.class);
                if (val==1)
                {
                    sendNotification(v,"Worker has been assigned to you.");

                }
                if (val==2)
                {
                    sendNotification(v,"Complaint has been resolved.");

                }
                myRef.setValue(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return true;
    }
    public void sendNotification(View view,String s) {
        Log.e("Ew1","Check3");

        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(c,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentTitle("FMS")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentText(s);



        // Gets an instance of the NotificationManager service//

        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(c);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//

        mNotificationManager.notify(Notification, mBuilder.build());
    }






    protected void onPostExecute(final Boolean success)
    {

    }
}