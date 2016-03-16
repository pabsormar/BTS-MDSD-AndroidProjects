package org.bts_netmind.contentprovidermanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper
{
    //CREATE TABLE AndroidDevelopment (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INT);
    private static final String CREATE_DB_TABLE = "CREATE TABLE " + ParamsDB.TABLE_NAME + "("
            + ParamsDB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ParamsDB.STUDENT_NAME + " TEXT, " + ParamsDB.STUDENT_AGE + " INT);";

    // The constructor is where the database is first created (done by the superclass)
    public MyDatabase(Context mContext)
    { super(mContext, ParamsDB.DB_NAME, null, ParamsDB.DB_VERSION); }

    // The considered table is created in 'onCreate()'
    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(MyDatabase.CREATE_DB_TABLE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
