package com.secretbiology.managesmart.database;

import android.database.sqlite.SQLiteDatabase;

public class Tables{

    //Constants
    static String TABLE_CATEGORIES = Categories.TABLE_CATEGORIES;
    static String CAT_ID = Categories.CAT_ID;
    static String CAT_NAME = Categories.CAT_NAME;

    static String TABLE_SUBCATEGORIES = SubCategories.TABLE_SUBCATEGORIES;
    static String SUBCAT_ID = SubCategories.SUBCAT_ID;
    static String SUBCAT_NAME = SubCategories.SUBCAT_NAME;
    static String SUBCAT_PARENT_ID = SubCategories.SUBCAT_PARENT_ID;


    //Table for storing parent category
    public void makeCategoryTable(SQLiteDatabase db) {
        String CREATE_CAT_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + CAT_ID + " INTEGER PRIMARY KEY,"
                + CAT_NAME + " TEXT )";
        db.execSQL(CREATE_CAT_TABLE);
    }

    //Table for storing subcategories
    public void makeSubcategoryTable (SQLiteDatabase db) {
        String CREATE_SUBCAT_TABLE = "CREATE TABLE " + TABLE_SUBCATEGORIES + "("
                + SUBCAT_ID + " INTEGER PRIMARY KEY,"
                + SUBCAT_PARENT_ID + " INTEGER,"
                + SUBCAT_NAME + " TEXT )";
        db.execSQL(CREATE_SUBCAT_TABLE);
    }

    //Drop all tables
    public void dropTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBCATEGORIES);
    }

}
