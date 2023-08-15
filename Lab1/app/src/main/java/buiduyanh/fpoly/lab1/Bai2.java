package buiduyanh.fpoly.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Bai2 extends AppCompatActivity {

    // khai báo
    private EditText Edt1 , Edt2 ;
    private Button BtnTimKiem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        // ánh xạ
        Edt1 = findViewById(R.id.Edt1);
        Edt2 = findViewById(R.id.Edt2);

        BtnTimKiem = findViewById(R.id.BtnTimKiem);
        Intent intent = new Intent(Bai2.this, MyService_Bai2.class);

        BtnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputChar = Edt2.getText().toString();
                char[] c =inputChar.toCharArray();

                String chuoicancheck = Edt1.getText().toString();
                intent.putExtra("char",c[0]);
                intent.putExtra("chk",chuoicancheck);
                startService(intent);
            }
        });
    }
}