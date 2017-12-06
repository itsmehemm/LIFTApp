package com.example.hemm.liftapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Created by hemm on 05/11/17.
 */

public class TabActivity extends Activity {

    WebView linkedinView;
    WebView instagramView;
    WebView facebookView;
    WebView twitterView;

    WebSettings linkedinViewSettings;
    WebSettings instagramViewSettings;
    WebSettings facebookViewSettings;
    WebSettings twitterViewSettings;

    String linkedinURL = "https://www.linkedin.com/";
    String instagramURL = "https://www.instagram.com";
    String facebookURL = "https://www.facebook.com";
    String twitterURL = "https://www.twitter.com";

    ProgressBar linkedinProgressBar;
    ProgressBar instaProgressBar;
    ProgressBar facebookProgressBar;
    ProgressBar twitterProgressBar;

    TabHost tabHost;
    String currentTab = "LinkedIn";

    public void setUpTabs() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("LinkedIn");
        tabSpec1.setIndicator("", getResources().getDrawable(R.drawable.linkedin));
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Instagram");
        tabSpec2.setIndicator("", getResources().getDrawable(R.drawable.instagram));
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Facebook");
        tabSpec3.setIndicator("", getResources().getDrawable(R.drawable.fb));
        tabSpec3.setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("Twitter");
        tabSpec4.setIndicator("", getResources().getDrawable(R.drawable.twitter));
        tabSpec4.setContent(R.id.tab4);
        tabHost.addTab(tabSpec4);

        tabHost.setCurrentTab(0);
        renderView();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                currentTab = tabId;
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
                renderView();
            }
        });
    }

    public void renderView() {
        switch (currentTab) {
            case "LinkedIn":
                renderLinkedInView();
                break;
            case "Instagram":
                renderInstagramView();
                break;
            case "Facebook":
                renderFacebookView();
                break;
            case "Twitter":
                renderTwitterView();
        }
    }
    public void setUpProgressBars() {
        linkedinProgressBar = (ProgressBar) findViewById(R.id.link_progressBar);
        instaProgressBar = (ProgressBar) findViewById(R.id.insta_progressBar);
        facebookProgressBar = (ProgressBar) findViewById(R.id.fb_progressBar);
        twitterProgressBar = (ProgressBar) findViewById(R.id.tw_progressBar);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        setUpTabs();
        setUpProgressBars();
        renderView();
    }

    public void renderLinkedInView() {
        linkedinView = (WebView) findViewById(R.id.linkedinView);
        linkedinView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                linkedinProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        linkedinViewSettings = linkedinView.getSettings();
        linkedinViewSettings.setJavaScriptEnabled(true);
        linkedinViewSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        linkedinView.loadUrl(linkedinURL);
    }

    public void renderInstagramView() {
        instagramView = (WebView) findViewById(R.id.instagramView);
        instagramView.setVerticalScrollBarEnabled(false);
        instagramView.setHorizontalScrollBarEnabled(false);
        instagramView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                instaProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        instagramViewSettings = instagramView.getSettings();
        instagramViewSettings.setJavaScriptEnabled(true);
        instagramViewSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        instagramView.loadUrl(instagramURL);
    }

    public void renderFacebookView() {
        facebookView = (WebView) findViewById(R.id.facebookView);
        facebookView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                facebookProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        facebookViewSettings = facebookView.getSettings();
        facebookViewSettings.setJavaScriptEnabled(true);
        facebookViewSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        facebookView.loadUrl(facebookURL);
    }

    public void renderTwitterView() {
        twitterView = (WebView) findViewById(R.id.twitterView);
        twitterView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                twitterProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        twitterViewSettings = twitterView.getSettings();
        twitterViewSettings.setJavaScriptEnabled(true);
        twitterViewSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        twitterView.loadUrl(twitterURL);
    }

    public void onBackPressed() {
        Log.i("Back Button Pressed for", currentTab);
        switch (currentTab) {
            case "LinkedIn":
                if (linkedinView.canGoBack()) {
                    linkedinView.goBack();
                    Log.i("Button task executed", currentTab);
                }
                break;
            case "Facebook":
                if (facebookView.canGoBack()) {
                    facebookView.goBack();
                    Log.i("Button task executed", currentTab);
                }
                break;
            case "Twitter":
                if (twitterView.canGoBack()) {
                    twitterView.goBack();
                    Log.i("Button task executed", currentTab);
                }
                break;
            case "Instagram":
                if (instagramView.canGoBack()) {
                    instagramView.goBack();
                    Log.i("Button task executed", currentTab);
                }
                break;
            default:
                Log.i("No back action", currentTab);
        }

    }

}
