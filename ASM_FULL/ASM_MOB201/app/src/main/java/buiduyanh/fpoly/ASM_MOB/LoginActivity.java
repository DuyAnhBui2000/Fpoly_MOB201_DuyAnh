package buiduyanh.fpoly.ASM_MOB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ASM_MOB.R;

import buiduyanh.fpoly.ASM_MOB.service.KiemTraDangNhapService;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class LoginActivity<callbackManager> extends AppCompatActivity {

    IntentFilter intentFilter;
    CallbackManager callbackManager;
    LoginButton btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ các view từ layout
        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        Button btnLogin = findViewById(R.id.btnLogin);

        intentFilter = new IntentFilter();
        intentFilter.addAction("kiemTraDangNhap");
        intentFilter.addAction("");

        // Xử lý sự kiện khi nhấn nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                // Tạo intent để khởi động service KiemTraDangNhapService
                Intent intent = new Intent(LoginActivity.this, KiemTraDangNhapService.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                bundle.putString("pass", pass);
                intent.putExtras(bundle);
                startService(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Đăng ký BroadcastReceiver để lắng nghe các sự kiện từ service
        registerReceiver(myBroadcast, intentFilter);
    }

    public BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "kiemTraDangNhap":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if(check){
                        // Nếu đăng nhập thành công, chuyển sang MainActivity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(context, "Sign in failed!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
