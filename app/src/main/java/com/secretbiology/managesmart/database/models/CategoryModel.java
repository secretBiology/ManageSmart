package com.secretbiology.managesmart.database.models;

public class CategoryModel {

    private int id;
    private String name;

    public CategoryModel(String name) {
        this.name = name;
    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel() {

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
}
