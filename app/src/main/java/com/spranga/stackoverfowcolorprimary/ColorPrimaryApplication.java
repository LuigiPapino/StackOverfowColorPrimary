package com.spranga.stackoverfowcolorprimary;

import android.app.Application;

/**
 * Created by Santosh on 3/24/17.
 */

public class ColorPrimaryApplication extends Application {

    private ApplicationAPI applicationAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesWrapper sharedPreferencesWrapper = new SharedPreferencesWrapper(this);
        applicationAPI = new ApplicationAPI(sharedPreferencesWrapper);
    }

    public ApplicationAPI getApplicationAPI(){
        return applicationAPI;
    }
}
