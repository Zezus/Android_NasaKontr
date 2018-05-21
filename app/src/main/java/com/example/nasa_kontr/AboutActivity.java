package com.example.nasa_kontr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textView1 = findViewById(R.id.ab_1_tv);
        textView2 = findViewById(R.id.ab_2_tv);
        webView = findViewById(R.id.ab_web);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");


        String date = getIntent().getExtras().getString("date");
        String title = getIntent().getExtras().getString("title");
        String url = getIntent().getExtras().getString("url");

        textView1.setText(date);
        textView2.setText(title);
        webView.loadUrl(url);
    }
}
