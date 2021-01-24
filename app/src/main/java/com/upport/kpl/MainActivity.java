package com.upport.kpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout noInternetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.wv_1);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        noInternetLayout = (RelativeLayout)findViewById(R.id.no_internet_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

        webView.setWebChromeClient(new MyChromeClient());
        webView.setWebViewClient(new BrowserClient(swipeRefreshLayout));

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);

        // Improve loading speeds
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);   // If uncommented, the page is displayed from cache when no network
        settings.enableSmoothTransition();

        loadWebPage();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }

    private void loadWebPage(){

        String url = "http://167.114.60.240/main.aspx";
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            webView.loadUrl(url);

            noInternetLayout.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
        else{
            noInternetLayout.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            Toast.makeText(this, "Check for Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void reconnectWebsite(View view) {
        loadWebPage();
    }
}