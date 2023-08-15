package buiduyanhph30569.fpoly.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ManHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

        HamChay3giay();
        // ánh xạ
        ImageView Img1 = findViewById(R.id.Img1);
        // gọi phương thức để gán hiệu ứng
        Animation anm1 = AnimationUtils.loadAnimation(this,R.anim.hieu_ung_man_hinh_chao);
        // bắt hiệu ứng vào Img hoặc TextView.
        Img1.startAnimation(anm1);

    }


    private void HamChay3giay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhCho.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}