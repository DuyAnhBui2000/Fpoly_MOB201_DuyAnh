package buiduyanh.fpoly.buiduyanh_xml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChonBai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_bai);

        Button BtnBaiXML = findViewById(R.id.BtnBaiXML);
        Button BtnBaiAnimation = findViewById(R.id.BtnBaiAnimation);


        BtnBaiXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ChonBai.this,MainActivity.class);
                startActivity(mIntent);
            }
        });

        BtnBaiAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ChonBai.this,BaiAnimation.class);
                startActivity(mIntent);
            }
        });
    }
}