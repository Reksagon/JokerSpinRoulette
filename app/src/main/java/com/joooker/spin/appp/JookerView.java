package com.joooker.spin.appp;

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

    FirebaseRemoteConfig gfR8xUZ238;
    ProgressBar p339HPaAv;
    WebView Zv57FmD8f7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joooker_view);
        p339HPaAv = findViewById(R.id.joooker_progress);
        Zv57FmD8f7 = findViewById(R.id.joooker_view);

        gfR8xUZ238 = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings joooker_FirebaseRometeConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        gfR8xUZ238.setDefaultsAsync(R.xml.joooker_url);
        gfR8xUZ238.setConfigSettingsAsync(joooker_FirebaseRometeConfigSettings);
        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!gfR8xUZ238.getString(new String(android.util.Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT)))
                .equals(new String(Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT))))
        {
            CookieManager win_CookieManager = CookieManager.getInstance();
            CookieManager.setAcceptFileSchemeCookies(true);
            win_CookieManager.setAcceptThirdPartyCookies(Zv57FmD8f7, true);

            Zv57FmD8f7.getSettings().setSupportZoom(true);
            Zv57FmD8f7.getSettings().setDomStorageEnabled(true);
            Zv57FmD8f7.loadUrl(gfR8xUZ238.getString(new String(Base64.decode("am9vb2tlcl91cmw=", Base64.DEFAULT))));
            Zv57FmD8f7.getSettings().setJavaScriptEnabled(true);
            Zv57FmD8f7.getSettings().setUseWideViewPort(true);
            Zv57FmD8f7.setWebChromeClient(new JoookerChrome(this, p339HPaAv));
            Zv57FmD8f7.setBackgroundColor(Color.WHITE);
            Zv57FmD8f7.getSettings().setLoadsImagesAutomatically(true);
            Zv57FmD8f7.getSettings().setLoadWithOverviewMode(true);
            Zv57FmD8f7.getSettings().setBuiltInZoomControls(false);
            Zv57FmD8f7.setWebViewClient(new JoookerWeb());
            Zv57FmD8f7.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            Zv57FmD8f7.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


            JoookerNetwork joookerNetwork = new JoookerNetwork(Zv57FmD8f7);
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
        if(Zv57FmD8f7.canGoBack()) Zv57FmD8f7.goBack();
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