package com.example.adi.frappybird;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class CollegeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);
        Bundle bundle = getIntent().getExtras();
        String URL = bundle.getString("Chosen University Link");
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://yocket.in" + URL);
    }
}
