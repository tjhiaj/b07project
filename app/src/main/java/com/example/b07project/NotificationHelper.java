package com.example.b07project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID = "andy_id";
    private static final String CHANNEL_NAME = "Andy Channel";
    private static final String CHANNEL_DESCRIPTION = "I DONT KNOW WHAT IM DOING";

    public static void showNotification(Context context, String title, String message, int notificationId) {
        createNotificationChannel(context);

        NotificationCompat.Builder builder = buildNotification(context, title, message);

        Intent intent = getIntentForNotificationId(context, notificationId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationId, intent,
                PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.notify(notificationId, builder.build());
        }

        startForegroundServiceForNotification(context, title, message);
    }

    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESCRIPTION);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private static NotificationCompat.Builder buildNotification(Context context, String title, String message) {
        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.pixel_kirby)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private static Intent getIntentForNotificationId(Context context, int notificationId) {
        Intent intent;

        if (NotificationType.checkNotificationType(notificationId) == "Announcement") {
            intent = new Intent(context, StudentAnnouncementsActivity.class);
        } else if (NotificationType.checkNotificationType(notificationId) == "Event") {
            intent = new Intent(context, EventViewActivity.class);
        } else {
            intent = new Intent(context, AdminOrStudentActivity.class);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private static void startForegroundServiceForNotification(Context context, String title, String message) {
        Intent serviceIntent = new Intent(context, NotificationPersistor.class);
        serviceIntent.putExtra("notificationTitle", title);
        serviceIntent.putExtra("notificationMessage", message);
        context.startForegroundService(serviceIntent);
    }
}
