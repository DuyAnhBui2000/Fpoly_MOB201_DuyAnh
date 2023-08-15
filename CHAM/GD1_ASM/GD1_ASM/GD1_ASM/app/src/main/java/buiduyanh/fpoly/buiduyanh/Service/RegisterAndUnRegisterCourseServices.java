package buiduyanh.fpoly.buiduyanh.Service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;

import buiduyanh.fpoly.buiduyanh.MyDatabase.DBHelper;
import buiduyanh.fpoly.buiduyanh.model.KhoaHoc;

public class RegisterAndUnRegisterCourseServices extends IntentService {

    public RegisterAndUnRegisterCourseServices() {
        super("RegisterAndUnRegisterCourseServices");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            DBHelper courseManagementSql = new DBHelper(getApplicationContext());

            Intent i = new Intent("RegisterAndUnRegisterCourseServices");
            final String action = intent.getAction();
            String idStu = intent.getStringExtra("idStudent");
            int idCou = intent.getIntExtra("idCourse", -1);
            boolean isRegister = intent.getBooleanExtra("isRegister", false);

            if (isRegister) {
                courseManagementSql.registerCourse(idStu, idCou); //đăng ký
            } else {
                courseManagementSql.unRegisterCourse(idStu, idCou); //hủy đăng ký
            }

            ArrayList<KhoaHoc> allCourse = courseManagementSql.getAllCourse();
            ArrayList<KhoaHoc> allCourseRegister = courseManagementSql.getAllCourseRegister(idStu);
            courseManagementSql.close();
            i.putExtra("allCourse", allCourse);
            i.putExtra("allCourseRegister", allCourseRegister);
            i.putExtra("action", action);
            i.putExtra("resultCode", Activity.RESULT_OK);
            LocalBroadcastManager.getInstance(this).sendBroadcast(i);
        }
    }
}
