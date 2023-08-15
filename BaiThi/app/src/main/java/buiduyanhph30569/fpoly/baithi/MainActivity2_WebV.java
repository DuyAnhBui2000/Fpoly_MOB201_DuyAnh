package buiduyanhph30569.fpoly.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity2_WebV extends AppCompatActivity {

    private WebView webView;
    public static final String EXTRA_URL = "extra_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_web_v);

        webView = findViewById(R.id.wvXML);

        webView.setWebViewClient(new WebViewClient());
        String url = getIntent().getStringExtra(EXTRA_URL);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}