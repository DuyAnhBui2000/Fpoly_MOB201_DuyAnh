package buiduyanh.fpoly.lab1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService_Bai1 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String ten = intent.getStringExtra("ten");
        String ma = intent.getStringExtra("ma");
        Toast.makeText(this, "                      Chạy Service \n Tên sinh viên : "+ten + "     Mã sinh viên : "+ ma, Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
