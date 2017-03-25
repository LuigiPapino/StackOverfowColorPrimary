package com.spranga.stackoverfowcolorprimary;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Santosh on 3/24/17.
 */

public class SharedPreferencesWrapper {
    private Context context;
    private SharedPreferences preferences;

    private static String KEY_THEME = "key_theme";

    SharedPreferencesWrapper(Context context){
        this.context = context;
        this.preferences = context.getSharedPreferences(context.getPackageName() + "app_prefs", Context.MODE_PRIVATE);
    }

    public void saveCurrentThemeKey(String value){
        saveStringValue(KEY_THEME,value);
    }

    public String getCurrentThemeKey(){
        return getStringValue(KEY_THEME,context.getString(R.string.theme_a_key));
    }


    private void saveStringValue(String key, String value) {
        preferences.edit().putString(context.getPackageName() + "_" + key, value).apply();
    }

    private String getStringValue(String key, String defaultValue) {
        return preferences.getString(context.getPackageName() + "_" + key, defaultValue);
    }
}
