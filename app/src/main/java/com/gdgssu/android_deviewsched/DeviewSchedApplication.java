package com.gdgssu.android_deviewsched;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.facebook.FacebookSdk;
import com.gdgssu.android_deviewsched.helper.LoginPreferenceHelper;

import com.gdgssu.android_deviewsched.model.User;
import com.gdgssu.android_deviewsched.model.UserItem;
import com.navercorp.volleyextensions.volleyer.factory.DefaultRequestQueueFactory;

public class DeviewSchedApplication extends Application{

    public static Context GLOBAL_CONTEXT;
    public static Boolean LOGIN_STATE;
    public static final String HOST_URL = "http://deview.unstabler.pl/";

    private static final String TAG = "DeviewSchedApplication";

    public static RequestQueue deviewRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        GLOBAL_CONTEXT = getApplicationContext();
        FacebookSdk.sdkInitialize(GLOBAL_CONTEXT);
        setLoginState();
        initRequestQueue();
    }

    public void setLoginState() {
        LoginPreferenceHelper prefHelper = new LoginPreferenceHelper(GLOBAL_CONTEXT);
        LOGIN_STATE = prefHelper.getPrefLoginValue(LoginPreferenceHelper.PREF_LOGIN_STATE, false);
    }

    public static void initRequestQueue() {
        deviewRequestQueue = DefaultRequestQueueFactory.create(GLOBAL_CONTEXT);
        deviewRequestQueue.start();
    }
}
