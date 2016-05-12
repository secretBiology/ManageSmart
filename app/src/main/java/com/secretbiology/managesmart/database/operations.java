package com.secretbiology.managesmart.database;

import android.database.sqlite.SQLiteDatabase;

import com.secretbiology.managesmart.database.queries.categoryMethods;

public class Operations implements categoryMethods {
    public SQLiteDatabase db;
    public Operations(SQLiteDatabase db) {
        this.db = db;
    }


    @Override
    public void addCategoy(SQLiteDatabase db) {

    }

    @Override
    public void addID() {

    }
}
