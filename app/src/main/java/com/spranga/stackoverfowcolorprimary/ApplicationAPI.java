package com.spranga.stackoverfowcolorprimary;

/**
 * Created by Santosh on 3/24/17.
 */

public class ApplicationAPI {
    private SharedPreferencesWrapper storageWrapper;

    ApplicationAPI(SharedPreferencesWrapper storageWrapper){
        this.storageWrapper = storageWrapper;
    }

    public SharedPreferencesWrapper getSharedPreferencesWrapper(){
        return storageWrapper;
    }
}
