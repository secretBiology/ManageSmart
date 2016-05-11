package com.secretbiology.managesmart.database.models;

import java.util.List;

public class category {

    private int id;
    private String name;
    private List<subcategory> subcategoryList;

    public category(int id, String name, List<subcategory> subcategoryList) {
        this.id = id;
        this.name = name;
        this.subcategoryList = subcategoryList;
    }

    public category() {
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

    public List<subcategory> getSubcategoryList() {
        return subcategoryList;
    }

    public void setSubcategoryList(List<subcategory> subcategoryList) {
        this.subcategoryList = subcategoryList;
    }
}
