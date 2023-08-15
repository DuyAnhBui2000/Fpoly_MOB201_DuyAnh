package buiduyanh.fpoly.notificationdemo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class MyApplication extends Application {

    public static final String CHANEL_ID="CHANEL_NOTI";
    @Override
    public void onCreate() {
        super.onCreate();

        CreateChanelNotification();
    }

    private void CreateChanelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANEL_ID,
                    "notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(null,null); // tat tieng
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
