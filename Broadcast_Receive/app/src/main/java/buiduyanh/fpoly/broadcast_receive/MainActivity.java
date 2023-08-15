package buiduyanh.fpoly.broadcast_receive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView TvHienThi;
    private EditText EdtNhap;
    private Button BtnSend;
    private  String ACTION = "receiver";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String s = bundle.getString("data");
            TvHienThi.setText(s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TvHienThi = findViewById(R.id.TvHienThi);
        EdtNhap = findViewById(R.id.EdtNhap);
        BtnSend = findViewById(R.id.BtnSend);

        BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent miIntent = new Intent(ACTION);
                    Bundle bundle = new Bundle();
                    bundle.putString("data" , EdtNhap.getText().toString());
                    miIntent.putExtras(bundle);
                    sendBroadcast(miIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ACTION);
        registerReceiver(receiver , filter );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}