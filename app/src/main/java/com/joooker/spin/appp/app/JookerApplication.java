package com.joooker.spin.appp.app;

import android.app.Application;

import com.onesignal.OneSignal;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class JookerApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        YandexMetricaConfig yaconfig = YandexMetricaConfig.newConfigBuilder("73765eaf-f643-431a-842a-a91c5ba54848").build();
        OneSignal.initWithContext(getApplicationContext());
        YandexMetrica.activate(getApplicationContext(), yaconfig);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        YandexMetrica.enableActivityAutoTracking(this);
        OneSignal.setAppId("32c04aab-daa4-4d7e-9c28-eb11fa1123bc");
    }
}
