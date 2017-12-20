package com.secretbiology.managesmart.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static com.secretbiology.managesmart.database.ExpenseSubCategory.TABLE_NAME;


/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

@Entity(tableName = TABLE_NAME)
public class ExpenseSubCategory {
    static final String TABLE_NAME = "ex_subcategory";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "timestamp")
    private Date timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
