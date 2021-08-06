package com.joooker.spin.appp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.joooker.spin.appp.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class JoookerSplash extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.purple);
        configSplash.setLogoSplash(R.mipmap.ic_launcher);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
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
    }

    @Override
    public void animationsFinished() {
        Intent joookerIntent = new Intent(JoookerSplash.this, JookerView.class);
        startActivity(joookerIntent);
        finish();
    }
}