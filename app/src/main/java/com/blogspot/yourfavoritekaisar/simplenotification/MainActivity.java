package com.blogspot.yourfavoritekaisar.simplenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtTittle, edtContent;
    public static final int NOTIFICATION_ID = 1;

    // ini bebas
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "IDN Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTittle = findViewById(R.id.edt_Tittle);
        edtContent = findViewById(R.id.edt_Content);
    }

    public void sendNotification(View view) {
        // Mensetting notification manager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_notifications_active))
                .setContentTitle(edtTittle.getText().toString())
                .setContentText(edtContent.getText().toString())
                .setAutoCancel(true);

        // Untuk Android oreo ke atas perlu menambahkan notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager !=null){
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();
        if (mNotificationManager !=null){
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }

    }
}
