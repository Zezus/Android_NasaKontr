package com.example.nasa_kontr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        String date = getIntent().getExtras().getString("date");
        String title = getIntent().getExtras().getString("title");

        textView1.setText(date);
        textView2.setText(title);
    }
}
