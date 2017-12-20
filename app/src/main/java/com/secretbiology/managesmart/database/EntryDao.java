package com.secretbiology.managesmart.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

@Dao
public interface EntryDao {
    @Query("SELECT * FROM ex_entry")
    List<ExpenseEntry> getAllEntries();

    @Insert
    long addEntry(ExpenseEntry entry);

    // Must provide all fields
    @Update
    int updateEntry(ExpenseEntry entry);

    @Query("UPDATE ex_entry SET category = :catID, subCategory = :subCatID")
    void updateAllCats(int catID, int subCatID);

    @Query("UPDATE ex_entry SET lastModified = :timestamp")
    void allModified(Date timestamp);


    @Query("DELETE FROM ex_entry " +
            "WHERE id LIKE :entryID "
    )
    void deleteEntryByID(int entryID);
}
