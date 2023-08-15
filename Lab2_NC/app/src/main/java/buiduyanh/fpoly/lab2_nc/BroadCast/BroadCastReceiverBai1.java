package buiduyanh.fpoly.lab2_nc.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadCastReceiverBai1 extends BroadcastReceiver {
    private TelephonyManager telephonyManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        // gọi service xử lý
        this.telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // tạo đối tượng xử lý
        MyPhoneState myPhoneState = new MyPhoneState(context);
        // gọi đến phương thức lắng nghe cuộc gọi
        this.telephonyManager.listen(myPhoneState, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public class MyPhoneState extends PhoneStateListener {
        private Context context;

        public MyPhoneState(Context context) {
            this.context = context;
        }

        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            if (state == 1)// nếu có cuộc gọi đến
            {
                Toast.makeText(context, "Bạn đang có cuộc gọi đến ", Toast.LENGTH_LONG).show();
            }
            super.onCallStateChanged(state, phoneNumber);
        }
    }
}
