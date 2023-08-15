package buiduyanh.fpoly.ASM_MOB.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;

import buiduyanh.fpoly.ASM_MOB.dao.DangKyMonHocDAO;
import buiduyanh.fpoly.ASM_MOB.model.MonHoc;

import java.util.ArrayList;

public class DKMonHocService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //nhan data
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        boolean isAll = bundle.getBoolean("isAll");

        DangKyMonHocDAO dangKyMonHocDAO = new DangKyMonHocDAO(this);
        ArrayList<MonHoc> list = dangKyMonHocDAO.getDSMonHoc(id, isAll);

        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putSerializable("list", list);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("DSMonHoc");
        sendBroadcast(intentBR);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
