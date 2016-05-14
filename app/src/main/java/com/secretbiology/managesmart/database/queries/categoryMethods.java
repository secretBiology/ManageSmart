package com.secretbiology.managesmart.database.queries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.secretbiology.managesmart.database.DataBase;

public class categoryMethods extends DataBase {

    SQLiteDatabase writable_db;
    SQLiteDatabase readable_db ;

    public categoryMethods(Context context) {
        super(context);
        this.writable_db = super.getWritableDatabase();
        this.readable_db = super.getReadableDatabase();
    }

    public void addCategoy() {

    }

    private void addID() {

    }

}
