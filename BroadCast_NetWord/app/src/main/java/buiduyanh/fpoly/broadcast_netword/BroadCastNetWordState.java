package buiduyanh.fpoly.broadcast_netword;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;
import android.widget.Toast;

public class BroadCastNetWordState extends BroadcastReceiver {

    private String state = "";

    @Override
    public void onReceive(Context context, Intent intent) {


        boolean isConnected = isConnectInternet(context);
        if (isConnected) {
            state = "Đã bật mạng";
            Toast.makeText(context, "Kết nối internet: true", Toast.LENGTH_SHORT).show();
        } else {
            state = "Đã tắt mạng";
            Toast.makeText(context, "Kết nối internet: false", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnectInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
