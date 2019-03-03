package com.MentalHealth.mental.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.MentalHealth.mental.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
        SimpleDateFormat formatHourMin = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        return "Ngày" + " " + formatHourMin.format(date);
    }
    public static String getMonth() {
        SimpleDateFormat formatHourMin = new SimpleDateFormat("MM");
        Date date = new Date();
        return "Tháng" + " " + formatHourMin.format(date);
    }
    public static String getYear() {
        SimpleDateFormat formatHourMin = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return "Năm" + " " + formatHourMin.format(date);
    }
    public static boolean downLoadFileWithURL(String fileUrl) {
        int count;
        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isSDPresent == true) {
            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MTH");
                file.mkdirs();
                URL url = new URL(Utils.urlEncodeUTF(fileUrl));
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                // Output stream

                OutputStream output = new FileOutputStream(file.getAbsolutePath() + "/" + Utils.getNameFile(fileUrl));

                byte data[] = new byte[1024];
                while ((count = input.read(data)) != -1) {
                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();
                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", "" + Utils.urlEncodeUTF(fileUrl) + " ---" + fileUrl);
                return false;
            }
        } else {
            try {
                URL url = new URL(Utils.urlEncodeUTF(fileUrl));
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                // Output stream
                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/EMSV/" + Utils.getNameFile(fileUrl));
                byte data[] = new byte[1024];
                while ((count = input.read(data)) != -1) {
                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();
                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", "" + Utils.urlEncodeUTF(fileUrl) + " ---" + fileUrl);
                return false;
            }
        }
        return true;
    }
    public static String getNameFile(String pathFile) {
        String[] arraySplit = pathFile.split("/");
        return arraySplit[arraySplit.length - 1];
    }
    public static String urlEncodeUTF(String pathImageUrl) {
        String CHARTER_REPLACE = "________________";
        String fileNameImage = Utils.getNameFile(pathImageUrl);
        String fileNameEndCode = "";
        try {
            fileNameEndCode = URLEncoder.encode(fileNameImage.replaceAll(" ", CHARTER_REPLACE), "UTF-8").replaceAll(CHARTER_REPLACE, "%20");
        } catch (Exception e) {
            Log.e("Error not endCode Image", "" + fileNameImage);
            e.printStackTrace();
            return pathImageUrl;
        }
        return pathImageUrl.replace(fileNameImage, fileNameEndCode);
    }

}
