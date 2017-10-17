package com.vbiralo.raspisanie;

import android.annotation.TargetApi;
import android.content.*;
import android.graphics.drawable.*;
import android.os.*;
import android.preference.*;
import android.support.v7.app.*;
import android.view.*;
import android.webkit.*;
import android.graphics.*;


public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);

        CookieSyncManager m = CookieSyncManager.createInstance(this);
        CookieManager c = CookieManager.getInstance();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NORMAL);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mWebView.loadUrl("http://sh.vbiralo.xyz/l/" + PreferenceManager.getDefaultSharedPreferences(this).getString("site_url", getString(R.string.site_url)));
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem refresh = menu.add("Refresh");
        refresh.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        Drawable icon_refresh = this.getResources().getDrawable(R.drawable.ic_refresh);
        icon_refresh.setTint(Color.WHITE);
        refresh.setIcon(icon_refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem p1) {
                mWebView.loadUrl(mWebView.getUrl());
                return false;
            }
        });
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onSettingsMenuClick(MenuItem item) {
        Intent intent = new Intent(this, MyPreferenceActivity.class);
        startActivity(intent);
    }

}
