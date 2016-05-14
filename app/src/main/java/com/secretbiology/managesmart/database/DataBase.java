package com.secretbiology.managesmart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.secretbiology.managesmart.constants.DataConstants;
import com.secretbiology.managesmart.database.queries.categoryMethods;

public class DataBase extends SQLiteOpenHelper  {

    private static final String TAG = DataBase.class.getSimpleName();

    Context mContext;
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context context) {
        super(context, DataConstants.DatabaseName, null, DataConstants.DatabaseVersion);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new Tables(db).makeAlltables();
        Log.i(TAG,"Database created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        new Tables(db).dropAllTables();
        new Tables(db).makeAlltables();
        Log.i(TAG,"Database successfully upgraded from version "+oldVersion+" to "+newVersion);
    }

}
