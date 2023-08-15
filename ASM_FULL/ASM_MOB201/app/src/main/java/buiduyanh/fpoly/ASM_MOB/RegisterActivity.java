package buiduyanh.fpoly.ASM_MOB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ASM_MOB.R;

import buiduyanh.fpoly.ASM_MOB.adapter.DangKiMonHocAdapter;
import buiduyanh.fpoly.ASM_MOB.model.MonHoc;
import buiduyanh.fpoly.ASM_MOB.service.DKMonHocService;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    RecyclerView recyclerRegisterCourse;
    int id;
    boolean isAll;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        recyclerRegisterCourse = findViewById(R.id.recyclerRegisterCourse);
        TextView txtCourse = findViewById(R.id.txtCourse);
        intentFilter = new IntentFilter();
        intentFilter.addAction("DSMonHoc");
        intentFilter.addAction("RegisterCourse");

        // Lấy id của người dùng
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        id = sharedPreferences.getInt("id", -1);

        // Lấy giá trị isAll từ Intent
        Intent intentIsAll = getIntent();
        Bundle bundleIsAll = intentIsAll.getExtras();
        isAll = bundleIsAll.getBoolean("isAll");
        if (isAll) {
            txtCourse.setText("REGISTER COURSE");
        } else {
            txtCourse.setText("REGISTERED COURSE");
        }

        // Khởi chạy DKMonHocService để lấy danh sách môn học
        Intent intent = new Intent(RegisterActivity.this, DKMonHocService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putBoolean("isAll", isAll);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void loadData(ArrayList<MonHoc> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerRegisterCourse.setLayoutManager(linearLayoutManager);
        DangKiMonHocAdapter adapter = new DangKiMonHocAdapter(this, list, id, isAll);
        recyclerRegisterCourse.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcast);
    }

    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "DSMonHoc":
                case "RegisterCourse":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check", true);
                    if (check) {
                        ArrayList<MonHoc> list = (ArrayList<MonHoc>) bundle.getSerializable("list");
                        loadData(list);
                    } else {
                        Toast.makeText(context, "Something was wrong! Please try again!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
