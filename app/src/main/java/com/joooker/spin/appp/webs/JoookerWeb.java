package com.joooker.spin.appp.webs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.joooker.spin.appp.JookerView;
import com.joooker.spin.appp.view.JoookerSplash;
import com.unity3d.player.UnityPlayerActivity;

public class JoookerWeb extends WebViewClient {

    Activity context;

    public JoookerWeb(Activity context) {
        this.context = context;
    }

    public JoookerWeb() {
        this.context = null;
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if(context != null) {
            if (url.contains(JookerView.gfR8xUZ238.getString("joooker_url_add"))) {
                Intent i = new Intent(context, UnityPlayerActivity.class);
                context.startActivity(i);
                context.finish();

            } else {
                Intent joookerIntent = new Intent(context, JookerView.class);
                context.startActivity(joookerIntent);
                context.finish();
            }
        }
        else super.onPageStarted(view,url,favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cudGlrdG9rLmNvbS8=", Base64.DEFAULT)))
                ||! url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cubGlua2VkaW4uY29tLw==", Base64.DEFAULT)))
                ||!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cuaW5zdGFncmFtLmNvbS8=", Base64.DEFAULT)))
                ||!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cubGlua2VkaW4uY29tLw==", Base64.DEFAULT)))
                ||!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cuZmFjZWJvb2suY29tLw==", Base64.DEFAULT))))
            view.loadUrl(url);
        return true;
    }
}
