package com.gdgssu.android_deviewsched.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreferenceHelper {

    private final String PREF_NAME = "com.gdgssu.loginpref";

    public final static String PREF_LOGIN_STATE = "PREF_LOGIN_STATE";

    static Context mContext;

    public LoginPreferenceHelper(Context context) {
        this.mContext = context;
    }

    public void setPrefLoginValue(String key, Boolean value){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getPrefLoginValue(String key, boolean defaultValue){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

        try{
            return pref.getBoolean(key, defaultValue);
        }catch(Exception e){
            return defaultValue;
        }
    }
}
