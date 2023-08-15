package buiduyanh.fpoly.buiduyanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText EdtTen, EdtPass;
    private Button BtnDangNhap;
    private TextView BtnDangKy;

    private String TaiKhoan, MatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EdtTen = findViewById(R.id.txtusername);
        EdtPass = findViewById(R.id.txtpassword);

        BtnDangNhap = findViewById(R.id.btndangnhap);
        BtnDangKy = findViewById(R.id.btndangky);

        BtnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = getIntent();
                Bundle bundle = mIntent.getExtras();
                if (bundle != null){
                    String username  = bundle.getString("User");
                    String pass  = bundle.getString("Pass");
                    boolean u = username.equals(EdtTen.getText().toString());
                    boolean p = pass.equals(EdtPass.getText().toString());
                    if (u&&p){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        BtnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this,DangKyActivity.class);
                startActivity(mIntent);
            }
        });
    }
}