package buiduyanh.fpoly.lab1;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService_Bai2 extends IntentService {


    public MyService_Bai2() {
        super("MyService_Bai2");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        char c1 =intent.getCharExtra("char",'0');
        String chuoicancheck = intent.getStringExtra("chk");
        count = demkytu(chuoicancheck,c1);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Số ký tự:  "+count, Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
    int count = 0;
    public int demkytu(String str, char c){
        int dem = 0;
        for (int i = 0; i<str.length();i++){
            if (str.charAt(i) == c){
                dem++;
            }
        }
        return dem;
    }
}
