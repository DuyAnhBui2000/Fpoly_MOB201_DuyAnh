package buiduyanh.fpoly.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // khai báo
    private Button BtnBatDau , BtnKetThuc;
    private EditText edtNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ánh xạ
        BtnBatDau = findViewById(R.id.BatDau);
        BtnKetThuc = findViewById(R.id.KetThuc);
        edtNhap = findViewById(R.id.edtNhap);

        BtnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start service
                Intent intent = new Intent(MainActivity.this , MyService.class);
                intent.putExtra("key", edtNhap.getText().toString());
                startService(intent); // chạy startService vì đang chạy service , bình thường là StarActiviti.

            }
        });

        BtnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Stop service
                Intent intent = new Intent(MainActivity.this , MyService.class);
                stopService(intent); // chạy stop.
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("log service","onDestroy Activity");
    }
}