package com.joooker.spin.appp.view;

import android.util.Base64;
import android.webkit.WebView;

import com.daimajia.androidanimations.library.Techniques;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.joooker.spin.appp.JookerView;
import com.joooker.spin.appp.R;
import com.joooker.spin.appp.webs.JoookerWeb;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class JoookerSplash extends AwesomeSplash {

    WebView ww;
    @Override
    public void initSplash(ConfigSplash configSplash) {
        setContentView(R.layout.activity_joooker_splash);
        ww = findViewById(R.id.ww);
        JookerView.gfR8xUZ238 = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings joooker_FirebaseRometeConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        JookerView.gfR8xUZ238.setDefaultsAsync(R.xml.joooker_url);
        JookerView.gfR8xUZ238.setConfigSettingsAsync(joooker_FirebaseRometeConfigSettings);

        configSplash.setBackgroundColor(R.color.purple);
        configSplash.setLogoSplash(R.mipmap.ic_launcher);
        configSplash.setRevealFlagX(Flags.WITH_LOGO);
        configSplash.setRevealFlagY(Flags.REVEAL_TOP);
        configSplash.setAnimLogoSplashTechnique(Techniques.Wobble);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setTitleTextSize(30);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setTitleTextColor(R.color.purple2);
        configSplash.setTitleSplash("Joker Spin");
        configSplash.setTitleFont("fonts/casino.ttf");
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.ZoomInUp);
        ww.setWebViewClient(new JoookerWeb(this));

    }

    @Override
    public void animationsFinished() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ww.loadUrl(JookerView.gfR8xUZ238.getString(new String(Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT))));
            }
        };
        ww.post(runnable);
        runnable.run();

    }
}