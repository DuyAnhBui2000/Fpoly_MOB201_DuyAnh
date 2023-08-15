package buiduyanh.fpoly.lab2_nc.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadCastRecieverBai3 extends BroadcastReceiver {
    private String voucher;

    @Override
    public void onReceive(Context context, Intent intent) {
        voucher = intent.getStringExtra("voucher");
        Log.d("voucher", voucher);
        checkEvent(context);
    }

    private void checkEvent(Context context) {
        if (this.voucher.length() != 0) {
            if (this.voucher.length() == 9) {
                if (this.voucher.startsWith("MEM") || this.voucher.startsWith("VIP")) {
                    if (this.voucher.equals("MEM537128")) {
                        Toast.makeText(context, "Khuyến mãi 10%", Toast.LENGTH_LONG).show();
                    } else if (this.voucher.equals("MEM537129")) {
                        Toast.makeText(context, "Khuyến mãi 20%", Toast.LENGTH_LONG).show();
                    } else if (this.voucher.equals("VIP537128")) {
                        Toast.makeText(context, "Khuyến mãi 30%", Toast.LENGTH_LONG).show();
                    } else if (this.voucher.equals("VIP537129")) {
                        Toast.makeText(context, "Khuyến mãi 50%", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "không khuyến mãi!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Vui lòng nhập đúng chuỗi bắt đầu bằng “MEM” hay “VIP”", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(context, "Vui lòng nhập đủ 9 ký tự", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Vui lòng nhập ký tự vào", Toast.LENGTH_LONG).show();
        }


    }
}
