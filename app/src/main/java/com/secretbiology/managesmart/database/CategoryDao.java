package com.secretbiology.managesmart.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM ex_category")
    List<ExpenseCategory> getAllCategories();

    @Insert
    long addCategory(ExpenseCategory category);

    @Update
    int updateCategory(ExpenseCategory category);

    @Query("DELETE FROM ex_category")
    void deleteAll();

    @Query("SELECT * FROM ex_category WHERE id = :catID")
    ExpenseCategory getCategoryByID(int catID);

    // Will return 0 of not found
    @Query("SELECT DISTINCT id FROM ex_category WHERE UPPER(name) LIKE UPPER(:catName)")
    long isAlreadyThere(String catName);
}
