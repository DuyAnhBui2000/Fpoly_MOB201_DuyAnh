package buiduyanh.fpoly.ASM_MOB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ASM_MOB.R;

import buiduyanh.fpoly.ASM_MOB.adapter.DangKiMonHocAdapter;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearCourse, linearMap, linearNews, linearSocial;
    DangKiMonHocAdapter dangKiMonHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view từ layout
        linearCourse = findViewById(R.id.linearCourse);
        linearMap = findViewById(R.id.linearMap);
        linearNews = findViewById(R.id.linearNews);
        linearSocial = findViewById(R.id.linearSocial);

        // Xử lý sự kiện khi nhấn vào mục Course
        linearCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CourseActivity.class));
            }
        });

        // Xử lý sự kiện khi nhấn vào mục Maps
        linearMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        // Xử lý sự kiện khi nhấn vào mục News
        linearNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });

        // Xử lý sự kiện khi nhấn vào mục Social
        linearSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SocialActivity.class));
            }
        });
    }
}
