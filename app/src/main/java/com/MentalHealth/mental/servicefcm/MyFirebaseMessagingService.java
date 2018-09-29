package com.MentalHealth.mental.servicefcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.MentalHealth.mental.MainActivity;
import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.constant.Constants;
import com.MentalHealth.mental.home.model.SlidingMenuModel;
import com.MentalHealth.mental.home.view.NavDrawerFragment;
import com.MentalHealth.mental.home.view.SlidingMenuAdapter;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.MentalHealth.mental.monthinfo.view.SlidingMonthAdapter;
import com.MentalHealth.mental.servicefcm.model.DBNotification;
import com.MentalHealth.mental.servicefcm.model.NotificationModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;
    private int notificationCount = 0;
    SharedPreferences sharedpreferences;
    public static final String MY_PREFERENCE = "Account";
    private NavDrawerFragment drawerFragment;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().toString());
//            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            JSONObject json = null;
            try {
                json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void handleDataMessage(JSONObject json) {
        sharedpreferences = getApplicationContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        Log.e(TAG, "push json: " + json.toString());

        try {

            String title = json.getString("title");
            String id = json.getString("id");
            String type = json.getString("type");
            Log.e(TAG, "title: " + title);
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setType(type);
            notificationModel.setId(id);
            notificationModel.setTitle(title);

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.icon_app)
                                .setWhen(0)
                                .setColor(getApplicationContext().getResources().getColor(R.color.colorRed))
                                .setContentTitle(title)
                                .setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(""));
                Uri music = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                notificationIntent.putExtra(Constants.ID, id);
                notificationIntent.putExtra(Constants.TYPE, type);
                LocalBroadcastManager.getInstance(this).sendBroadcast(notificationIntent);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(contentIntent);
                builder.setFullScreenIntent(contentIntent, true);
                builder.setSound(music);
                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());

            } else {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.icon_app)
                                .setWhen(0)
                                .setColor(getApplicationContext().getResources().getColor(R.color.colorRed))
                                .setContentTitle((title))
                                .setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(""));
                Uri music = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                notificationIntent.putExtra(Constants.ID, id);
                notificationIntent.putExtra(Constants.TYPE, type);
                LocalBroadcastManager.getInstance(this).sendBroadcast(notificationIntent);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(contentIntent);
                builder.setFullScreenIntent(contentIntent, true);
                builder.setSound(music);
                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());


                // check for image attachment
//                if (TextUtils.isEmpty(imageUrl)) {
//                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
//                } else {
//                    // image is present, show notification with image
//                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
//                }
            }

        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}