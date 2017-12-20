package com.secretbiology.managesmart.common;

import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;
import com.secretbiology.managesmart.database.ExpenseSubCategory;

import java.util.List;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

public class AllFields {
    private List<ExpenseCategory> categoryList;
    private List<ExpenseSubCategory> subCategoryList;
    private List<ExpenseMedium> mediumList;

    public List<ExpenseCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ExpenseCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<ExpenseSubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<ExpenseSubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public List<ExpenseMedium> getMediumList() {
        return mediumList;
    }

    public void setMediumList(List<ExpenseMedium> mediumList) {
        this.mediumList = mediumList;
    }
}
