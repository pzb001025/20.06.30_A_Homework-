package com.example.e;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.e.Bean.Bean;
import com.example.e.Bean.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {

    private WebView web;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
        web.loadUrl(path);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEven(Event event) {
        path = event.getPath();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
