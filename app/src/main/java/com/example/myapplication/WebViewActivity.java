package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private EditText mUrlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.web_view);
        mUrlEditText = (EditText) findViewById(R.id.url_edit);

        // 요거 해 줘야 된다
        mWebView.setWebViewClient(new WebViewClient());
        // Javascript 사용하는 페이지를 볼 수 있게
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    public void showWebPage(View view) {
        String url = mUrlEditText.getText().toString();

        if (url.startsWith("http://") || url.startsWith("https://")) {
            mWebView.loadUrl(url);
        } else {
            mWebView.loadUrl("http://" + url);
        }
    }

    public void goBack(View view) {
        mWebView.goBack();
    }

    public void goForward(View view) {
        mWebView.goForward();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
