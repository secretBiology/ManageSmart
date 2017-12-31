package com.secretbiology.managesmart.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static com.secretbiology.managesmart.database.ExpenseEntry.ENTRY;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

@Entity(tableName = ENTRY)
public class ExpenseEntry {

    static final String ENTRY = "ex_entry";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "currency")
    private String currency;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "timestamp")
    private Date timestamp;

    @ColumnInfo(name = "lastModified")
    private Date lastModified;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "type")
    private int type; // 0 = debit, 1 = credit, 2 = debt, 3 = loan

    @ColumnInfo(name = "medium")
    private int medium;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "filePath")
    private String filePath;

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


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
