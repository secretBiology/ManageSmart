package com.secretbiology.managesmart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secretbiology.managesmart.database.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Database {

    //Get Constants
    static String TABLE_CATEGORIES = "CategoryTable";
    static String CAT_ID = "cat_id";
    static String CAT_NAME = "cat_name";


    SQLiteDatabase db;

    public Categories(Context context) {
        super(context);
        this.db = getWritableDatabase();
    }

    public void add(String categoryName){
        ContentValues values = new ContentValues();
        values.put(CAT_NAME, categoryName);
        db.insert(TABLE_CATEGORIES, null, values);
    }

    public CategoryModel get(int categoryID){
        Cursor cursor = db.query(TABLE_CATEGORIES, new String[]{CAT_ID, CAT_NAME},
                CAT_ID + "=?",
                new String[]{String.valueOf(categoryID)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        CategoryModel entry = null;
        if (cursor != null) {
            entry = new CategoryModel(cursor.getInt(0), cursor.getString(1));
            cursor.close();
        }
        return entry;
    }

    public List<CategoryModel> getAll() {
        List<CategoryModel> entryList = new ArrayList<CategoryModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CategoryModel model = new CategoryModel();
                model.setId(cursor.getInt(0));
                model.setName(cursor.getString(1));
                entryList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }

    public int update(CategoryModel entry) {
        ContentValues values = new ContentValues();
        values.put(CAT_NAME, entry.getName());
        return db.update(TABLE_CATEGORIES, values, CAT_ID + " = ?",
                new String[]{String.valueOf(entry.getId())});
    }

    public void delete(CategoryModel category) {
        db.delete(TABLE_CATEGORIES, CAT_ID + " = ?", new String[] { String.valueOf(category.getId()) });
        db.close();
    }

}
