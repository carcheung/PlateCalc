package com.carolyncheung.calc.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Carolyn Cheung on 2/28/2016.
 */
public class SharedPreferencesHelper {
    public static final String USER_SETUP = "usersSetup";

    /* Put functions for sharedPreferences */
    public static void putInt(Context context, String key, int value) {
        // use getDefaultSharedPreferences to get preferences shared across all activities
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putBool(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.contains(key);
    }

    /* Get functions for getting key values */
    // will take in a default value, return defaultVal if it doesnt exist
    public static int getInt(Context context, String key, int defaultVal) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, defaultVal);
    }

    public static String getString(Context context, String key, String defaultVal) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, defaultVal);
    }

    public static boolean getBool(Context context, String key, boolean defaultVal) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, defaultVal);
    }
}
