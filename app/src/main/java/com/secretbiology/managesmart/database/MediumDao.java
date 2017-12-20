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
public interface MediumDao {

    @Query("SELECT * FROM ex_medium")
    List<ExpenseMedium> getAllMediums();

    @Insert
    long addMedium(ExpenseMedium medium);

    @Update
    int updateMedium(ExpenseMedium medium);
}
