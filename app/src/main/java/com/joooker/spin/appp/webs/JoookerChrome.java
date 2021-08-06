package com.joooker.spin.appp.webs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.core.content.FileProvider;

import com.joooker.spin.appp.constanta.JoookerC;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JoookerChrome extends WebChromeClient {
    Activity activity;
    ProgressBar progressBar;

    private File JoookerInImage() throws IOException {
        String joookerDate = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String joookerImg = "Joooker" + joookerDate + "_";
        File joookerFileToDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(joookerImg, ".jpg", joookerFileToDir);
    }

    public JoookerChrome(Activity activity, ProgressBar progressBar) {
        this.activity = activity;
        this.progressBar = progressBar;
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        Dexter.withContext(activity).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) { }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) { }
                }).check();
        JoookerC.joookerCallBac = filePathCallback;
        Intent joookerIntentOne = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File joookerFile = null;
        try {
            joookerFile = JoookerInImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(joookerFile != null)
        {
            Uri joookerForFile = FileProvider.getUriForFile(activity, activity.getApplication().getPackageName() + ".provider", joookerFile);
            JoookerC.joookerUrl = joookerForFile;
            joookerIntentOne.putExtra(MediaStore.EXTRA_OUTPUT, joookerForFile);
            joookerIntentOne.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent joookerIntentTwo = new Intent(Intent.ACTION_GET_CONTENT);
            joookerIntentTwo.addCategory(Intent.CATEGORY_OPENABLE);
            joookerIntentTwo.setType("image/*");
            Intent[] winIntents = {joookerIntentTwo};
            Intent joookerIntentChooser = new Intent(Intent.ACTION_CHOOSER);
            joookerIntentChooser.putExtra(Intent.EXTRA_INTENT, joookerIntentOne);
            joookerIntentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, winIntents);

            activity.startActivityForResult(joookerIntentChooser, JoookerC.Code);

            return true;
        }
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        progressBar.setProgress(newProgress);
        if(newProgress < 100 && progressBar.getVisibility() == progressBar.GONE)
            progressBar.setVisibility(progressBar.VISIBLE);
        if(newProgress == 100)
            progressBar.setVisibility(ProgressBar.GONE);
    }

}
