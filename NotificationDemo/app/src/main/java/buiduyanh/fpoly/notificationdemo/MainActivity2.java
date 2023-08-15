package buiduyanh.fpoly.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView Tv_kq ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Tv_kq = findViewById(R.id.Tv_kq);
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            String s = bundle.getString("data");
            Tv_kq.setText(s);
        }
    }
}