package buiduyanh.fpoly.buiduyanh.Service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;

import buiduyanh.fpoly.buiduyanh.MyDatabase.DBHelper;
import buiduyanh.fpoly.buiduyanh.model.KhoaHoc;

public class GetAllCourseServices extends IntentService {

    public GetAllCourseServices() {
        super("GetAllCourseServices");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            DBHelper courseManagementSql = new DBHelper(getApplicationContext());

            Intent i = new Intent("GetAllCourseServices");
            final String action = intent.getAction();
            String idStu = intent.getStringExtra("idStudent");
            boolean isMine = intent.getBooleanExtra("isMine", false);

            if (!isMine) {
                ArrayList<KhoaHoc> allCourse = courseManagementSql.getAllCourse();
                i.putExtra("allCourse", allCourse);
            }

            ArrayList<KhoaHoc> allCourseRegister = courseManagementSql.getAllCourseRegister(idStu);
            i.putExtra("allCourseRegister", allCourseRegister);
            courseManagementSql.close();
            i.putExtra("action", action);
            i.putExtra("resultCode", Activity.RESULT_OK);
            LocalBroadcastManager.getInstance(this).sendBroadcast(i);
        }
    }
}
