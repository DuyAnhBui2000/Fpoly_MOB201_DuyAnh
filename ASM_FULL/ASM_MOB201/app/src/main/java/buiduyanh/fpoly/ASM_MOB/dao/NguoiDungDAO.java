package buiduyanh.fpoly.ASM_MOB.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import buiduyanh.fpoly.ASM_MOB.helper.DbHelper;

public class NguoiDungDAO {
    private DbHelper dbHelper;
    private SharedPreferences sharedPreferences;

    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
    }

    // Kiểm tra thông tin đăng nhập
    public boolean kiemTraDangNhap(String user, String pass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE username = ? AND password = ?", new String[]{user, pass});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id", cursor.getInt(0));
            editor.apply();
            return true;
        }
        return false;
    }
}
