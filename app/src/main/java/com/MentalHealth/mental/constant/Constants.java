package com.MentalHealth.mental.constant;

import android.content.Context;

/**
 * Created by hoangphuc on 27/02/2018.
 */
public class Constants {
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final String PUSH_NOTIFICATION_LIVE = "pushNotificationLive";//push noti from firebase service to live news and live sumary
    public static final String PUSH_NOTIFICATION_GOAL = "pushNotificationGoal";//push goal to top from live news
    public static final String PUSH_NOTIFICATION_SESSION = "pushNotificationEVENT";//push session to live tab from live news
    public static final String PUSH_NOTIFICATION_TIME = "pushNotificationTIME";//push session when user in screen live to sync time
    public static final String PUSH_NOTIFICATION_TIME_ID = "pushNotificationTIMEID";//push timeID to customize event content when null.
    public static final String PUSH_MATCH_END = "pushNotificationMATCHEND";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";

}