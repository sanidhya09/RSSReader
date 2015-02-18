package com.knn.rssreader.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CreateNewsListTable {

    public static final String TABLE_NAME = "tb_news_list";

    public static final String TB_ID = "_id";
    public static final String TB_HEADLINE = "headline";
    public static final String TB_ORIGINAL = "original";
    public static final String TB_THUMBNAIL = "thumbnail";
    public static final String TB_NEWSID = "newsid";
    public static final String TB_TIME = "time";

    private final Context context;
    public static MyDataBaseHelper DBHelper;
    public static SQLiteDatabase db;
    Cursor cur;

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
            + "(" + TB_ID + " INTEGER PRIMARY KEY autoincrement ," + TB_ORIGINAL
            + " TEXT ," + TB_HEADLINE + " TEXT ,"
            + TB_NEWSID + " TEXT not null ,"
            + TB_THUMBNAIL + " TEXT ," + TB_TIME
            + " TEXT);";

    public CreateNewsListTable(Context ctx) {
        Log.i(TABLE_NAME, "database called");
        this.context = ctx;
        DBHelper = new MyDataBaseHelper(context);

    }

    public CreateNewsListTable open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public void insert(String newsID, String original, String thumbnail, String headline,
                       String time) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TB_THUMBNAIL, thumbnail);
        initialValues.put(TB_NEWSID, newsID);
        initialValues.put(TB_ORIGINAL, original);
        initialValues.put(TB_HEADLINE, headline);
        initialValues.put(TB_TIME, time);

        db.insert(TABLE_NAME, null, initialValues);

    }

    public static void truncate() {
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public static void deletetable() {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public static void createtable() {
        db.execSQL(TABLE_CREATE);
    }

    public Cursor selectAll() {
        try {
            String query = "SELECT * FROM " + TABLE_NAME;
            System.out.println(query);
            cur = db.rawQuery(query, null);

            cur.moveToFirst();
            return cur;
        } catch (Exception ex) {
            return null;
        }


    }

    public Cursor getNumberOfRecords() {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        System.out.println(query);
        cur = db.rawQuery(query, null);
        cur.moveToFirst();
        return cur;
    }

//	public Cursor getAllNewsList(int group_id, String userName) {
//
//		Cursor cur = null;
//		String query = "SELECT * FROM " + TABLE_NAME +" ;";
//		System.out.println(query);
//		cur = db.rawQuery(query, null);
//		System.out.println(cur.getCount());
//		return cur;
//	}
//	02-17 12:54:56.291: D/Retrofit(5875):         {
//	02-17 12:54:56.291: D/Retrofit(5875):             "newsid": "1154",
//	02-17 12:54:56.291: D/Retrofit(5875):             "headline": "New app to help you get better Wi-Fi around the house",
//	02-17 12:54:56.291: D/Retrofit(5875):             "thumbnail": "http://static.financialexpress.com/pic/uploadedImages/mediumImages//M_Id_481787_Wifi.jpg",
//	02-17 12:54:56.291: D/Retrofit(5875):             "original": "http://static.financialexpress.com/pic/uploadedImages/mediumImages//M_Id_481787_Wifi.jpg",
//	02-17 12:54:56.291: D/Retrofit(5875):             "time": "96 days ago"
//	02-17 12:54:56.291: D/Retrofit(5875):         }

}
