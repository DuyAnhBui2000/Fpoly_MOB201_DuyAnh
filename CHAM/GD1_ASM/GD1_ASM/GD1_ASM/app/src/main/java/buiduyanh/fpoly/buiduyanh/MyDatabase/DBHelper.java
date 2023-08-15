package buiduyanh.fpoly.buiduyanh.MyDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import buiduyanh.fpoly.buiduyanh.R;
import buiduyanh.fpoly.buiduyanh.model.KhoaHoc;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CourseManagement";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db = this.getReadableDatabase();


    private static final String TABLE_REGISTRATION_INFOR = "infoRegistration";
    private static final String COL_ID = "id";

    private static final String TABLE_STUDENT= "tbStudent";
    private static final String COL_ID_STUDENT = "idStudent";
    private static final String COL_NAME = "name";
    private static final String COL_BIRTH = "birth";
    private static final String COL_ADDRESS = "address";
    private static final String COL_NUMBER_PHONE = "phone";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCourse = "CREATE TABLE  tbCourse" +
                " (idCourse INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,courseName TEXT NOT NULL,schedule TEXT NOT NULL," +
                "testSchedule TEXT NOT NULL,idCoursePhoto INTEGER NOT NULL );";
        db.execSQL(sqlCourse);
        String listCourse = "INSERT INTO " + KhoaHoc.TABLE_NAME + " (" + KhoaHoc.COL_ID_COURSE + ", " + KhoaHoc.COL_NAME_COURSE + ", "
                + KhoaHoc.COL_SCHEDULE + ", " + KhoaHoc.COL_TEST_SCHEDULE + ", " + KhoaHoc.COL_PHOTO + ") " +
                "Values ('1', 'Lập trình Flutter', 'Ca 2 - 246', '02/11/2022' "+"," + R.drawable.ic_course_flutter +")," +
                "('2', 'Lập trình Java', 'Ca4 - 357', '05/03/2022'"+"," + R.drawable.ic_course_java +")," +
                "('3', 'Python cho người mới bắt đầu', 'Ca4 - 246', '05/03/2022'"+"," + R.drawable.ic_course_python +")," +
                "('4', 'Cơ sở dữ liệu SQL', 'Ca3 - 246', '10/04/2022'"+"," + R.drawable.ic_course_sql +")," +
                "('5', 'Kotlin cơ bản', 'Ca1 - 357', '21/03/2022'"+"," + R.drawable.ic_course_kotlin +")," +
                "('6', 'Lập trình C/C++ cơ bản', 'Ca6 - 357', '22/03/2022'"+"," + R.drawable.ic_course_c +")," +
                "('7', 'Lập trình web với JavaScript', 'Ca2 - 357', '08/05/2022'"+"," + R.drawable.ic_course_js +"), " +
                "('8', 'Lập trình game với C#', 'Ca5 - 357', '05/03/2022'"+"," + R.drawable.ic_course_c_shap +"), " +
                "('9', 'Lập trình đa nền tảng React Native', 'Ca4 - 246', '17/03/2022'"+"," + R.drawable.ic_course_react +"), " +
                "('10', 'Làm quen với công nghệ BlockChain', 'Ca1 - 246', '01/03/2022'"+"," + R.drawable.ic_course_block_chain +")";
        db.execSQL(listCourse);
        String sqlStudent= "CREATE TABLE " + TABLE_STUDENT +
                "(" +
                COL_ID_STUDENT + " TEXT PRIMARY KEY, " +
                COL_NAME + " TEXT, " +
                COL_BIRTH + " TEXT," +
                COL_ADDRESS + " TEXT," +
                COL_NUMBER_PHONE + " TEXT" +
                ")";
        db.execSQL(sqlStudent);


        String sqlRegister = "CREATE TABLE " + TABLE_REGISTRATION_INFOR +
                "(" +
                COL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COL_ID_STUDENT + " TEXT, " +
                KhoaHoc.COL_ID_COURSE + " INTEGER" +
                ")";
        db.execSQL(sqlRegister);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + KhoaHoc.TABLE_NAME);
        db.execSQL("drop table if exists " + TABLE_STUDENT);
        db.execSQL("drop table if exists " + TABLE_REGISTRATION_INFOR);
        onCreate(db);
    }

    public ArrayList<KhoaHoc> getAllCourse() {
        Cursor cursor = db.rawQuery("SELECT * FROM " + KhoaHoc.TABLE_NAME, null);
        ArrayList<KhoaHoc> lists = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                KhoaHoc objCourse = new KhoaHoc();
                objCourse.setId(cursor.getInt(0));
                objCourse.setCourseName(cursor.getString(1));
                objCourse.setSchedule(cursor.getString(2));
                objCourse.setTestSchedule(cursor.getString(3));
                objCourse.setIdCoursePhoto(cursor.getInt(4));
                lists.add(objCourse);
                cursor.moveToNext();
            }
        }
        return lists;
    }


    public void registerCourse(String idStudent, int idCourse) {
        ContentValues values = new ContentValues();
        values.put(COL_ID_STUDENT, idStudent);
        values.put(KhoaHoc.COL_ID_COURSE, idCourse);

        db.insert(TABLE_REGISTRATION_INFOR, null, values);
    }


    public void unRegisterCourse(String idStudent, int idCourse) {
        db.delete(TABLE_REGISTRATION_INFOR,"idStudent=? AND idCourse=?", new String[]{idStudent, String.valueOf(idCourse)});
    }


    public ArrayList<KhoaHoc> getAllCourseRegister(String idStudent) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + KhoaHoc.TABLE_NAME + " course, " + TABLE_REGISTRATION_INFOR
                + " tbInfo WHERE course.idCourse = tbInfo.idCourse AND tbInfo.idStudent LIKE '" + idStudent + "'", null);
        ArrayList<KhoaHoc> list = new ArrayList<>();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new KhoaHoc(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
