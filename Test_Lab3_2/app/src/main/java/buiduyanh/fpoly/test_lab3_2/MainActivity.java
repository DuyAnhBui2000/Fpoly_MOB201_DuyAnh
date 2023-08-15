package buiduyanh.fpoly.test_lab3_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<BookmarkItem> bookmarkItems;
    private BookmarkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        bookmarkItems = new ArrayList<>();
        adapter = new BookmarkAdapter(this, bookmarkItems);
        listView.setAdapter(adapter);

        // Kiểm tra quyền truy cập vào lịch sử trình duyệt
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_HISTORY_BOOKMARKS)
                != PackageManager.PERMISSION_GRANTED) {
            // Quyền chưa được cấp, yêu cầu người dùng cấp quyền
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_HISTORY_BOOKMARKS},
                    PERMISSION_REQUEST_CODE);
        } else {
            // Quyền đã được cấp, tải dữ liệu bookmark
            loadBookmarks();
        }
}}