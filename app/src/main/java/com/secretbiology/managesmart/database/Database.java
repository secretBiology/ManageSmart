package com.secretbiology.managesmart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    //Constants
    private static String DATABASE_NAME = "ManageSmart.db";
    private static int DATABASE_VERSION = 1;

    Context mContext;

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new Tables().makeCategoryTable(db);
        new Tables().makeSubcategoryTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        new Tables().dropTables(db);
        new Tables().makeCategoryTable(db);
        new Tables().makeSubcategoryTable(db);
    }
}
