package buiduyanh.fpoly.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bai1 extends AppCompatActivity {

    // khai báo
    private EditText Edt1 , Edt2 ;
    private Button BtnStart , BtnStop ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        // ánh xạ
        Edt1 = findViewById(R.id.Edt1);
        Edt2 = findViewById(R.id.Edt2);

        BtnStart = findViewById(R.id.btnStart);
        BtnStop = findViewById(R.id.btnStop);

        Intent intent = new Intent(Bai1.this , MyService_Bai1.class);

        // bắt sự kiện click cho BtnStart.
        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = Edt1.getText().toString();
                String ma = Edt2.getText().toString();
                intent.putExtra("ten",ten);
                intent.putExtra("ma",ma);
                startService(intent);

            }
        });

        // bắt sự kiện click cho BtnStop.
        BtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopService(intent);
                Toast.makeText(Bai1.this, "Đã dừng Service!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}