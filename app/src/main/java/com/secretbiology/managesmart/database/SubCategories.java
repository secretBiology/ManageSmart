package com.secretbiology.managesmart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secretbiology.managesmart.database.models.SubCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategories  {

    //Get Constants
    static String TABLE_SUBCATEGORIES = "SubCategoryTable";
    static String SUBCAT_ID = "subcat_id";
    static String SUBCAT_NAME = "subcat_name";
    static String SUBCAT_PARENT_ID = "subcat_parent";


   SQLiteDatabase db;

    public SubCategories (SQLiteDatabase db) {
        this.db = db;
    }

    public void add(String subcategoryName, int parentID){
        ContentValues values = new ContentValues();
        values.put(SUBCAT_NAME, subcategoryName);
        values.put(SUBCAT_PARENT_ID, parentID);
        db.insert(TABLE_SUBCATEGORIES, null, values);
    }

    public SubCategoryModel get(int subcategoryID){
        Cursor cursor = db.query(TABLE_SUBCATEGORIES, new String[]{SUBCAT_ID, SUBCAT_PARENT_ID, SUBCAT_NAME},
                SUBCAT_ID + "=?",
                new String[]{String.valueOf(subcategoryID)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        SubCategoryModel entry = null;
        if (cursor != null) {
            entry = new SubCategoryModel(cursor.getInt(0),cursor.getInt(1), cursor.getString(2));
            cursor.close();
        }
        return entry;
    }

    public List<SubCategoryModel> getAll() {
        List<SubCategoryModel> entryList = new ArrayList<SubCategoryModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_SUBCATEGORIES;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                SubCategoryModel model = new SubCategoryModel();
                model.setId(cursor.getInt(0));
                model.setParentID(cursor.getInt(1));
                model.setName(cursor.getString(2));
                entryList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }

    public int update(SubCategoryModel entry) {
        ContentValues values = new ContentValues();
        values.put(SUBCAT_PARENT_ID,entry.getParentID());
        values.put(SUBCAT_NAME, entry.getName());
        return db.update(TABLE_SUBCATEGORIES, values, SUBCAT_ID + " = ?",
                new String[]{String.valueOf(entry.getId())});
    }

    public void delete(SubCategoryModel subcategory) {
        db.delete(TABLE_SUBCATEGORIES, SUBCAT_ID + " = ?", new String[] { String.valueOf(subcategory.getId()) });
        db.close();
    }

}
