package buiduyanh.fpoly.buiduyanh_xml;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainWeb extends AppCompatActivity {
    private WebView webView;
    public static final String EXTRA_URL = "extra_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);

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