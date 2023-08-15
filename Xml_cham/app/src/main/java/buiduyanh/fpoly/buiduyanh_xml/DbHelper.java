package buiduyanh.fpoly.buiduyanh_xml;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static final String NAME = "DBMyXML";
    public static final int VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String taoBang = "CREATE TABLE MyXML(" +
                "idXML INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL," +
                "noidung TEXT NOT NULL," +
                "thoigian TEXT NOT NULL," +
                "link TEXT NOT NULL," +
                "imgavt TEXT NOT NULL)";
        db.execSQL(taoBang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String xoaBang = "DROP TABLE IF EXISTS MyXML";
        db.execSQL(xoaBang);
    }

    public ArrayList<Item> getAllCourse() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MyXML", null);
        ArrayList<Item> arr = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Item myItem = new Item();
                myItem.setIdXML(cursor.getInt(0));
                myItem.setTitle(cursor.getString(1));
                myItem.setDescription(cursor.getString(2));
                myItem.setPubDate(cursor.getString(3));
                myItem.setLink(cursor.getString(4));
                myItem.setImgAvt(cursor.getString(5));
                arr.add(myItem);
                cursor.moveToNext();
            }
        }
        return arr;
    }

    public long insert(Item item) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", item.getTitle());
        values.put("noidung", item.getDescription());
        values.put("thoigian", item.getPubDate());
        values.put("link", item.getLink());
        values.put("imgavt", item.getImgAvt());
        return db.insert("MyXML", null, values);
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MyXML", null, null);
        db.close();
    }
}
