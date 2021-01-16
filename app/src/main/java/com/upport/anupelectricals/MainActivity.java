package com.upport.anupelectricals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView1;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView1 = (WebView)findViewById(R.id.wv_1);
        url = "http://51.79.147.49/electricals.aspx";


        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl(url);

        webView1.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView1.canGoBack()) {
            this.webView1.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }
}