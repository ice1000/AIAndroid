package com.tesla.ai;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.net.URL;

import util.BaseActivity;

public class GithubActivity extends BaseActivity {

    private WebView github;
    private TextView noNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

		initToolBar();

        github = (WebView) findViewById(R.id.github);
        noNetwork = (TextView) findViewById(R.id.noNetwork);

//        github.setWebViewClient(new WebViewClient());
//        github.setWebChromeClient(new WebChromeClient());

        if(isNetworkConnected(this)){
            noNetWorkConnection();
        }
        else {
            String githubURL = "https://github.com/ice1000/AIAndroid";
            try {
                URL gitURL = new URL(githubURL);
                if (gitURL.getFile() == null) throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                noNetWorkConnection();
            } finally {
                noNetWorkConnection();
            }

            github.loadUrl(githubURL);
            github.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            github.getSettings().setJavaScriptEnabled(true);
            github.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }});
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && github.canGoBack()) {
            github.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

//    public void gotoSettings(View view){
//        startActivity(new Intent(this, SettingsActivity.class));
////        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	    return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    private void noNetWorkConnection(){

        github.setVisibility(View.GONE);
        noNetwork.setVisibility(View.VISIBLE);
//        github.loadUrl("file:///android_res/raw/no_network.html");

    }

}

