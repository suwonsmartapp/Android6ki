package com.example.javascriptcall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://suwonsmartapp.iptime.org/test/junsuk/javainterface.php");

        webView.addJavascriptInterface(new JavascriptInterface(this), "androidJs");
    }

    private static class JavascriptInterface {

        private Context mContext;

        public JavascriptInterface(Context context) {
            mContext = context;
        }

        @android.webkit.JavascriptInterface
        public void callback(String message) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }
}
