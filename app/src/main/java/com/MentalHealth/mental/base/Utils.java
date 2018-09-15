package com.MentalHealth.mental.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.MentalHealth.mental.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    public static String getCurrentDay() {
        String date = "";
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                date = "Chủ nhật";
                break;
            case Calendar.MONDAY:
                date = "Thứ 2";
                break;
            case Calendar.TUESDAY:
                date = "Thứ 3";
                break;
            case Calendar.WEDNESDAY:
                date = "Thứ 4";
                break;
            case Calendar.THURSDAY:
                date = "Thứ 5";
                break;
            case Calendar.FRIDAY:
                date = "Thứ 6";
                break;
            case Calendar.SATURDAY:
                date = "Thứ 7";
                break;
        }
        return date;
    }

    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

    public static void showCustomToask(Context context, String msg, int duration) {
        try {
            Toast toast = Toast.makeText(context, msg, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.bg_custom_toast);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatHourMin = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        return "Ngày" + " " + formatHourMin.format(date);
    }

}
