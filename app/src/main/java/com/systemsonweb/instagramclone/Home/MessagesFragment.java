package com.systemsonweb.instagramclone.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.systemsonweb.instagramclone.R;


public class MessagesFragment extends Fragment {
    private static final String TAG = "MessagesFragment";
    private WebView webView ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        webView = (WebView) view.findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.google.com");
        webView.loadUrl("https://bnrhvprod-ssb.desu.edu/dsu/kiosk/homepage_selector.htm");

        return view;
        //return webView;
    }





}