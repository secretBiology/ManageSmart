package com.secretbiology.managesmart.database;

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
public interface SubCategoryDao {

    @Query("SELECT * FROM ex_subcategory")
    List<ExpenseSubCategory> getAllSubCategories();

    @Insert
    long addSubCategory(ExpenseSubCategory subCategory);

    @Update
    int updateSubCategory(ExpenseSubCategory subCategory);

    @Query("DELETE FROM ex_subcategory")
    void deleteAll();

}
