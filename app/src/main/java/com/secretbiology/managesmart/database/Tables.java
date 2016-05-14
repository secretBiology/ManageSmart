package com.secretbiology.managesmart.database;

import android.database.sqlite.SQLiteDatabase;
import com.secretbiology.managesmart.constants.DataConstants;


public class Tables {

    SQLiteDatabase db;

    public Tables(SQLiteDatabase db) {
        this.db = db;
    }

    public void makeAlltables(){
        makeCategoryTable();
        makesubCategoryTable();
    }

    private void makeCategoryTable(){
        String CREATE_TABLE = "CREATE TABLE " + DataConstants.CategoryTableName + "("
                + DataConstants.CategoryID + " INTEGER,"
                + DataConstants.subCategoryName + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void makesubCategoryTable(){
        String CREATE_TABLE = "CREATE TABLE " + DataConstants.subCategoryTableName + "("
                + DataConstants.subCategoryID + " INTEGER PRIMARY KEY,"
                + DataConstants.subCategoryName + " TEXT,"
                + DataConstants.subCategoryParent + "INTEGER )";
        db.execSQL(CREATE_TABLE);
    }

    public void dropAllTables(){
        db.execSQL("DROP TABLE IF EXISTS " + DataConstants.CategoryTableName);
        db.execSQL("DROP TABLE IF EXISTS " + DataConstants.subCategoryTableName);
    }
}
