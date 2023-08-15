package buiduyanh.fpoly.lab2_nc.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCastReceiverBai2 extends BroadcastReceiver {
    private String enterString;

    @Override
    public void onReceive(Context context, Intent intent) {
        enterString = intent.getStringExtra("enterString");
        if (enterString.length() != 0){
            Toast.makeText(context, enterString, Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Vui lòng nhập ký tự vào", Toast.LENGTH_LONG).show();
        }
    }
}
