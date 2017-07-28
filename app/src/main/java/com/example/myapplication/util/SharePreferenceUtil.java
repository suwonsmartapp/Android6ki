package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by junsuk on 2017. 7. 28..
 */

public class SharePreferenceUtil {
    public static void saveWeather(Context context, String weather) {
        // 저장
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("weather", weather);

        // Commit the edits!    비동기
        editor.apply();
    }

    public static String restoreWeather(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("weather", "맑음");
    }
}
