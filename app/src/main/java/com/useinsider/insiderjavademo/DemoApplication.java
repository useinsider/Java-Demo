package com.useinsider.insiderjavademo;

import android.app.Application;

import timber.log.Timber;

import com.useinsider.insider.Insider;
import com.useinsider.insider.InsiderCallback;
import com.useinsider.insider.InsiderCallbackType;

import org.json.JSONObject;

public class DemoApplication extends Application {
    private static DemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // TODO: Please change with your partner name.
        // Make sure that all the letters are lowercase.
        Insider.Instance.init(this, "your_partner_name");

        Insider.Instance.registerInsiderCallback(new InsiderCallback() {
            @Override
            public void doAction(JSONObject data, InsiderCallbackType callbackType) {
                switch (callbackType) {
                    case NOTIFICATION_OPEN:
                        Timber.tag("[INSIDER]").d("[NOTIFICATION_OPEN]: " + data);
                        break;
                    case INAPP_SEEN:
                        Timber.tag("[INSIDER]").d("[INAPP_BUTTON_CLICK]: " + data);
                        break;
                    case TEMP_STORE_CUSTOM_ACTION:
                        Timber.tag("[INSIDER]").d("[TEMP_STORE_CUSTOM_ACTION]: " + data);
                        break;
                }
            }
        });

        // TODO: Add your splash activity.
        //Insider.Instance.setSplashActivity(Splash.activity);

        Insider.Instance.getCurrentUser().setLocationOptin(true);
        Insider.Instance.startTrackingGeofence();
        Insider.Instance.getCurrentUser().setLocale("tr_TR");
    }
} 