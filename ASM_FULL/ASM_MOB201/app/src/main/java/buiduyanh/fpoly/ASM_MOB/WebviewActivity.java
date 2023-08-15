package buiduyanh.fpoly.ASM_MOB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ASM_MOB.R;

public class WebviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Khởi tạo WebView và ProgressBar từ layout
        WebView webView = findViewById(R.id.webView);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Nhận intent từ activity trước đó
        Intent intent = getIntent();

        // Lấy đường dẫn trang web từ intent
        String link = intent.getStringExtra("linkNews");

        // Load trang web vào WebView
        webView.loadUrl(link);

        // Thiết lập WebViewClient để kiểm soát sự tải trang và ẩn ProgressBar khi trang đã tải xong
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
