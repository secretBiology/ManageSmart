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

    static String TABLE_EXPENSE = Expense.TABLE_EXPENSE;
    static String EXP_ID = Expense.EXP_ID;
    static String EXP_CAT_ID = Expense.EXP_CAT_ID;
    static String EXP_SUBCAT_ID = Expense.EXP_SUBCAT_ID;
    static String EXP_TIMESTAMP = Expense.EXP_TIMESTAMP;
    static String EXP_AMOUNT = Expense.EXP_AMOUNT;
    static String EXP_CURRENCY = Expense.EXP_CURRENCY;
    static String EXP_DATE = Expense.EXP_DATE;
    static String EXP_TIME = Expense.EXP_TIME;
    static String EXP_NAME = Expense.EXP_NAME;
    static String EXP_PLACE = Expense.EXP_PLACE;
    static String EXP_METHOD = Expense.EXP_METHOD;
    static String EXP_NOTES = Expense.EXP_NOTES;


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

    //Table for storing Expenses
    public void makeExpenseTable (SQLiteDatabase db){
        String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_EXPENSE + "("
                + EXP_ID + " INTEGER PRIMARY KEY,"
                + EXP_CAT_ID + " INTEGER,"
                + EXP_SUBCAT_ID + " INTEGER,"
                + EXP_TIMESTAMP + " TEXT,"
                + EXP_AMOUNT + " REAL,"
                + EXP_CURRENCY + " TEXT,"
                + EXP_DATE + " TEXT,"
                + EXP_TIME + " TEXT,"
                + EXP_NAME + " TEXT,"
                + EXP_PLACE + " TEXT,"
                + EXP_METHOD + " TEXT,"
                + EXP_NOTES + " TEXT )";
        db.execSQL(CREATE_EXPENSE_TABLE);
    }

    //Drop all tables
    public void dropTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBCATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
    }

}
