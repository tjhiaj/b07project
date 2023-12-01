package com.example.b07project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID = "andy_id";
    private static final String CHANNEL_NAME = "Andy Channel";
    private static final String CHANNEL_DESCRIPTION = "I DONT KNOW WHAT IM DOING";

    public static void showNotification(Context context, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager == null) {
            return;
        }

        // Create a Notification Channel for devices running Android Oreo (API 26) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }

        android.app.Notification.Builder builder = new android.app.Notification.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.pixel_kirby) // Set your notification icon here
                .setContentTitle(title)
                .setContentText(message);

        // Display the notification
        notificationManager.notify(0, builder.build());
    }
}
