package com.knn.rssreader.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "info";
	public static final String DATABASE_NAME = "snb_chat";

	public MyDataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(" Sanidhya09 ", "table create was called");
		db.execSQL(CreateNewsListTable.TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + CreateNewsListTable.TABLE_NAME);
		onCreate(db);

	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		super.onDowngrade(db, oldVersion, newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + CreateNewsListTable.TABLE_NAME);
		onCreate(db);

	}

}
