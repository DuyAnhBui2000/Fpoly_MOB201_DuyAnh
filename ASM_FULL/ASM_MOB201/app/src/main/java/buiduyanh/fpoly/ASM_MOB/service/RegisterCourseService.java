package buiduyanh.fpoly.ASM_MOB.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import buiduyanh.fpoly.ASM_MOB.dao.DangKyMonHocDAO;
import buiduyanh.fpoly.ASM_MOB.model.MonHoc;

import java.util.ArrayList;

public class RegisterCourseService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", -1);
        String code = bundle.getString("code", "");
        int isRegister = bundle.getInt("isRegister", -1);
        boolean isAll = bundle.getBoolean("isAll");

        boolean check;
        DangKyMonHocDAO dangKyMonHocDAO = new DangKyMonHocDAO(this);
        if(isRegister == id){
            check = dangKyMonHocDAO.huyDangKyMonHoc(id, code);
        }else {
            check = dangKyMonHocDAO.dangKyMonHoc(id, code);
        }

        ArrayList<MonHoc> list = new ArrayList<>();
        if (check){
            list = dangKyMonHocDAO.getDSMonHoc(id,isAll);
        }
        //send data (list) to
        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putSerializable("list", list);
        bundleBR.putBoolean("check", check);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("RegisterCourse");
        sendBroadcast(intentBR);

        return super.onStartCommand(intent, flags, startId);
    }

    public RegisterCourseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}