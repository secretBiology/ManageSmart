package com.secretbiology.managesmart.common;

import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseEntry;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.List;

/**
 * Created by Dexter on 12/20/2017.
 */

public class AllFields {
    private List<ExpenseCategory> categoryList;
    private List<ExpenseMedium> mediumList;
    private List<ExpenseEntry> entryList;

    private ExpenseCategory currentCategory;
    private ExpenseMedium currentMedium;

    public List<ExpenseCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ExpenseCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<ExpenseMedium> getMediumList() {
        return mediumList;
    }

    public void setMediumList(List<ExpenseMedium> mediumList) {
        this.mediumList = mediumList;
    }

    public List<ExpenseEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<ExpenseEntry> entryList) {
        this.entryList = entryList;
    }

    public ExpenseCategory getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(ExpenseCategory currentCategory) {
        this.currentCategory = currentCategory;
    }

    public ExpenseMedium getCurrentMedium() {
        return currentMedium;
    }

    public void setCurrentMedium(ExpenseMedium currentMedium) {
        this.currentMedium = currentMedium;
    }

}
