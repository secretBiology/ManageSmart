package com.secretbiology.managesmart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secretbiology.managesmart.database.models.ExpenseModel;

import java.util.ArrayList;
import java.util.List;

public class Expense extends Database {

    //Constants
    static String TABLE_EXPENSE = "CategoryExpense";
    static String EXP_ID ="exp_id";
    static String EXP_CAT_ID ="exp_categoryID";
    static String EXP_SUBCAT_ID ="exp_subcategoryID";
    static String EXP_TIMESTAMP ="exp_timestamp";
    static String EXP_AMOUNT ="exp_amount";
    static String EXP_CURRENCY ="exp_currency";
    static String EXP_DATE ="exp_date";
    static String EXP_TIME ="exp_time";
    static String EXP_NAME ="exp_name";
    static String EXP_PLACE ="exp_place";
    static String EXP_METHOD ="exp_method";
    static String EXP_NOTES ="exp_notes";

    SQLiteDatabase db;

    public Expense(Context context) {
        super(context);
        this.db = getWritableDatabase();
    }

    public void add (ExpenseModel expense){
        ContentValues values = new ContentValues();
        values.put(EXP_CAT_ID, expense.getCategory());
        values.put(EXP_SUBCAT_ID, expense.getSubCategory());
        values.put(EXP_TIMESTAMP, expense.getTimestamp());
        values.put(EXP_AMOUNT, expense.getAmount());
        values.put(EXP_CURRENCY, expense.getCurrency());
        values.put(EXP_DATE, expense.getDate());
        values.put(EXP_TIME, expense.getTime());
        values.put(EXP_NAME, expense.getName());
        values.put(EXP_PLACE, expense.getPlace());
        values.put(EXP_METHOD, expense.getMethod());
        values.put(EXP_NOTES, expense.getNotes());
        db.insert(TABLE_EXPENSE, null, values);
    }

    public ExpenseModel get(int expenseID){
        Cursor cursor = db.query(TABLE_EXPENSE, new String[]{EXP_ID, EXP_CAT_ID, EXP_SUBCAT_ID, EXP_TIMESTAMP,EXP_AMOUNT, EXP_CURRENCY, EXP_DATE, EXP_TIME, EXP_NAME, EXP_PLACE, EXP_METHOD, EXP_NOTES},
                EXP_ID + "=?",
                new String[]{String.valueOf(expenseID)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ExpenseModel entry = null;
        if (cursor != null) {
            entry = new ExpenseModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getDouble(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                    cursor.getString(10), cursor.getString(11));
            cursor.close();
        }
        return entry;
    }

    public List<ExpenseModel> getAll() {
        List<ExpenseModel> entryList = new ArrayList<ExpenseModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ExpenseModel expense = new ExpenseModel();
                expense.setId(cursor.getInt(0));
                expense.setCategory(cursor.getInt(1));
                expense.setSubCategory(cursor.getInt(2));
                expense.setTimestamp(cursor.getString(3));
                expense.setAmount(cursor.getDouble(4));
                expense.setCurrency(cursor.getString(5));
                expense.setDate(cursor.getString(6));
                expense.setTime(cursor.getString(7));
                expense.setName(cursor.getString(8));
                expense.setPlace(cursor.getString(9));
                expense.setMethod(cursor.getString(10));
                expense.setNotes(cursor.getString(11));
                entryList.add(expense);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }

    public int update(ExpenseModel expense) {
        ContentValues values = new ContentValues();
        values.put(EXP_CAT_ID, expense.getCategory());
        values.put(EXP_SUBCAT_ID, expense.getSubCategory());
        values.put(EXP_TIMESTAMP, expense.getTimestamp());
        values.put(EXP_AMOUNT, expense.getAmount());
        values.put(EXP_CURRENCY, expense.getCurrency());
        values.put(EXP_DATE, expense.getDate());
        values.put(EXP_TIME, expense.getTime());
        values.put(EXP_NAME, expense.getName());
        values.put(EXP_PLACE, expense.getPlace());
        values.put(EXP_METHOD, expense.getMethod());
        values.put(EXP_NOTES, expense.getNotes());
        return db.update(TABLE_EXPENSE, values, EXP_ID + " = ?",
                new String[]{String.valueOf(expense.getId())});
    }

    public void delete(ExpenseModel expense) {
        db.delete(TABLE_EXPENSE, EXP_ID + " = ?", new String[] { String.valueOf(expense.getId()) });
        db.close();
    }

}

