package buiduyanh.fpoly.ASM_MOB.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "DANGKYMONHOC", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Tạo bảng NGUOIDUNG
        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(id integer primary key autoincrement, username text, password text, name text)";
        sqLiteDatabase.execSQL(dbNguoiDung);

        // Tạo bảng MONHOC
        String dbMonHoc = "CREATE TABLE MONHOC(code text primary key, name text, teacher text)";
        sqLiteDatabase.execSQL(dbMonHoc);

        // Tạo bảng THONGTIN
        String dbThongTin = "CREATE TABLE THONGTIN(id integer primary key autoincrement, code text, date text, address text)";
        sqLiteDatabase.execSQL(dbThongTin);

        // Tạo bảng DANGKY
        String dbDangKy = "CREATE TABLE DANGKY(id integer, code text)";
        sqLiteDatabase.execSQL(dbDangKy);

        // Thêm dữ liệu mẫu vào bảng NGUOIDUNG
        String insNguoiDung = "INSERT INTO NGUOIDUNG VALUES(1,'buiduyanh','duyanh','Duy Anh')";
        sqLiteDatabase.execSQL(insNguoiDung);

        // Thêm dữ liệu mẫu vào bảng MONHOC
        String insThongTin = "INSERT INTO MONHOC VALUES('MOB201','Android Nâng Cao','Nguyễn Văn Lộc'),('VIE1016','Chính trị','Đỗ Mai Phương'),('MOB2041','Dự án mẫu (LTMT)','Nguyễn Văn Lộc'),('AND101','Lập trình Android 1','Trần Thị Huệ')";
        sqLiteDatabase.execSQL(insThongTin);

        // Thêm dữ liệu mẫu vào bảng THONGTIN
        String insMonHoc = "INSERT INTO THONGTIN VALUES(1, 'MOB201', 'Ca 2 - 02/05/2023', 'P210'),(2, 'MOB201', 'Ca 2 - 01/05/2023', 'P202'),(3, 'MOB201', 'Ca 2 - 03/05/2023', 'P210'),(4, 'AND101', 'Ca 5 - 01/05/2023', 'P304'),(5, 'VIE1016', 'Ca 5 - 06/05/2023', 'F407'),(6, 'MOB2041', 'Ca 1 - 04/05/2023', 'Online - https://meet.google.com/row-mvgk-fog')";
        sqLiteDatabase.execSQL(insMonHoc);

        // Thêm dữ liệu mẫu vào bảng DANGKY
        String insDangKy = "INSERT INTO DANGKY VALUES(1,'MOB201'),(1,'AND101'),(2,'VIE1016')";
        sqLiteDatabase.execSQL(insDangKy);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Xóa các bảng cũ nếu có và tạo lại từ đầu khi có sự thay đổi version
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MONHOC");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THONGTIN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANGKY");
            onCreate(sqLiteDatabase);
        }
    }
}
