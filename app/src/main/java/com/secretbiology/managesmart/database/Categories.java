package com.secretbiology.managesmart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secretbiology.managesmart.database.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    //Get Constants
    static String TABLE_CATEGORIES = "CategoryTable";
    static String CAT_ID = "cat_id";
    static String CAT_NAME = "cat_name";


    SQLiteDatabase db;

    public Categories(Context context) {
        Database db = new Database(context);
        this.db = db.getWritableDatabase();
    }

    public int add(String categoryName){
        ContentValues values = new ContentValues();
        values.put(CAT_NAME, categoryName);
        int returnValue = (int) db.insert(TABLE_CATEGORIES, null, values);
        db.close();
        return returnValue;
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
        db.close();
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
        db.close();
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

    //Check if record is already ther
    public boolean isAlreadyThere(int fieldValue) {
        String Query = "SELECT * FROM " + TABLE_CATEGORIES + " WHERE " + CAT_ID + " = '" + fieldValue+"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){ cursor.close(); return false;
        } cursor.close(); return true; }

}
