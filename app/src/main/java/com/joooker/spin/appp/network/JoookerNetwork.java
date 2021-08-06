package com.joooker.spin.appp.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebView;

import androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker;

public class JoookerNetwork extends BroadcastReceiver {
    String joookerUrl;
    WebView webView;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager joookerManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo joookerNetworkInfo = joookerManager.getActiveNetworkInfo();

        if(joookerNetworkInfo != null && joookerNetworkInfo.isConnectedOrConnecting())
        {
            if(joookerUrl != null) webView.loadUrl(joookerUrl);
        }
        else
        {
            joookerUrl = webView.getUrl();
            webView.loadUrl("file:///android_asset/index.html");
        }
    }

    public JoookerNetwork(WebView webView) {
        this.webView = webView;
    }


}
