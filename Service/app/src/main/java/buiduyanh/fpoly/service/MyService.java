package buiduyanh.fpoly.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    //  extends Service.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // thêm onCreate , onStartCommand , onDestroy .
    // có thể dùng Toast hoặc Log.e

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("log service","onCreate"); //
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("log service","onStartCommand"); //
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("key");
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        return START_NOT_STICKY; //
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("log service","onDestroy"); //
    }
}
