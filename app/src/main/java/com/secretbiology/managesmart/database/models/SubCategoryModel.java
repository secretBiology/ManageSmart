package com.secretbiology.managesmart.database.models;

public class SubCategoryModel {

    private int id;
    private String name;
    private int parentID;

    public SubCategoryModel(int id, int parentID, String name) {
        this.id = id;
        this.name = name;
        this.parentID = parentID;
    }

    public SubCategoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
}
