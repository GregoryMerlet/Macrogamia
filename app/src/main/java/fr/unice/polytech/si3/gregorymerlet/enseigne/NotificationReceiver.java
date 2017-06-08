package fr.unice.polytech.si3.gregorymerlet.enseigne;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification-id";

    public void onReceive(Context context, Intent intent) {
        Intent myIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if(myIntent != null)
            myIntent.setAction("advantages");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);

        Notification myNotification = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.notification_Title))
                .setContentText(context.getString(R.string.notification_Content))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_name)
                .build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id, myNotification);
    }
}
