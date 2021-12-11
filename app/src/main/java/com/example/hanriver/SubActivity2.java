package com.example.hanriver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SubActivity2 extends AppCompatActivity {
    WebView mWebView;
    WebSettings mWebSettings;
    String url;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        Intent intent = getIntent();
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new HelloWebViewClient());
        mWebSettings = mWebView.getSettings(); //각종 환경 설정 가능여부
        mWebSettings.setJavaScriptEnabled(true); // 자바스크립트 허용여부
        url = intent.getStringExtra("bt"); // 요게 에러같은데
        mWebView.loadUrl((String)url);



    }
}


class HelloWebViewClient extends WebViewClient{

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}