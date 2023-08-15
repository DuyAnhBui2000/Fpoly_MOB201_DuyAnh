package buiduyanh.fpoly.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyListView = findViewById(R.id.historyListView);

        // Tạo danh sách lịch sử giả định
        ArrayList<BrowserHistoryItem> historyList = new ArrayList<>();
        historyList.add(new BrowserHistoryItem("Google", "https://www.google.com"));
        historyList.add(new BrowserHistoryItem("Facebook", "https://www.facebook.com"));
        historyList.add(new BrowserHistoryItem("OpenAI", "https://www.openai.com"));
        historyList.add(new BrowserHistoryItem("Google", "https://www.google.com"));
        historyList.add(new BrowserHistoryItem("Facebook", "https://www.facebook.com"));
        historyList.add(new BrowserHistoryItem("OpenAI", "https://www.openai.com"));
        historyList.add(new BrowserHistoryItem("Google", "https://www.google.com"));
        historyList.add(new BrowserHistoryItem("Facebook", "https://www.facebook.com"));
        historyList.add(new BrowserHistoryItem("OpenAI", "https://www.openai.com"));
        historyList.add(new BrowserHistoryItem("Google", "https://www.google.com"));
        historyList.add(new BrowserHistoryItem("Facebook", "https://www.facebook.com"));
        historyList.add(new BrowserHistoryItem("OpenAI", "https://www.openai.com"));
        historyList.add(new BrowserHistoryItem("Google", "https://www.google.com"));
        historyList.add(new BrowserHistoryItem("Facebook", "https://www.facebook.com"));
        historyList.add(new BrowserHistoryItem("OpenAI", "https://www.openai.com"));
        historyList.add(new BrowserHistoryItem("Google", "https://www.google.com"));
        historyList.add(new BrowserHistoryItem("Facebook", "https://www.facebook.com"));
        historyList.add(new BrowserHistoryItem("OpenAI", "https://www.openai.com"));


        // Tạo ArrayAdapter và gắn kết danh sách lịch sử với ListView
        ArrayAdapter<BrowserHistoryItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(adapter);

    }

}
