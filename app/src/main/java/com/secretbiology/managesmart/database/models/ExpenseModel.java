package com.secretbiology.managesmart.database.models;

import com.secretbiology.managesmart.common.Utilities;

public class ExpenseModel {

    private int id;
    private int category;
    private int subCategory;
    private String timestamp;
    private Double amount;
    private String currency;
    private String date;
    private String time;
    private String name;
    private String place;
    private String method;
    private String notes;

    public ExpenseModel(int id, int category, int subCategory, String timestamp, Double amount, String currency, String date, String time, String name, String place, String method, String notes) {
        this.id = id;
        this.category = category;
        this.subCategory = subCategory;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.time = time;
        this.name = name;
        this.place = place;
        this.method = method;
        this.notes = notes;
    }

    public ExpenseModel() {
    }

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

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private void setDefault(){
        this.category = 1; //First should be unknown category
        this.subCategory = 1; //First should be unknown category
        this.timestamp = new Utilities().timeStamp();
        this.amount = 0.0;
        this.currency = "IN";
        this.date = new Utilities().date();
        this.time = new Utilities().time();
        this.name = "Unknown";
        this.place = "Unknown";
        this.method = "Cash";
        this.notes = "N/A";
    }
}
