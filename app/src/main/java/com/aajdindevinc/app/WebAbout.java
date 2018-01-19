package com.aajdindevinc.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class WebAbout extends AppCompatActivity {

    WebView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_about);
        getSupportActionBar().hide();

        about = findViewById(R.id.aboutWeb);
        about.getSettings().setLoadsImagesAutomatically(true);
        about.getSettings().setJavaScriptEnabled(true);
        about.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        about.loadUrl("https://bmktz7.wixsite.com/tracebrace");

    }
}
