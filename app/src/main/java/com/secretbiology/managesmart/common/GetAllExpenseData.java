package com.secretbiology.managesmart.common;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.secretbiology.managesmart.database.AppData;
import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.Date;

public class GetAllExpenseData extends AsyncTask<Void, Void, Void> {

    private OnDataReceived dataReceived;
    private AppData appData;

    public GetAllExpenseData(Context context, OnDataReceived dataReceived) {
        this.dataReceived = dataReceived;
        this.appData = AppData.getDatabase(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AllFields allFields = new AllFields();
        allFields.setCategoryList(appData.categories().getAllCategories());
        allFields.setMediumList(appData.mediums().getAllMediums());
        allFields.setEntryList(appData.entries().getAllEntries());

        if (allFields.getCategoryList().size() == 0) {
            Log.i(getClass().getSimpleName(), "No category found. Creating default.");
            ExpenseCategory category = new ExpenseCategory();
            category.setName("Unknown");
            category.setTimestamp(new Date());
            category.setDetails("Default category created by App");
            int catID = (int) appData.categories().addCategory(category);
            category.setId(catID);
            allFields.getCategoryList().add(category);
        }

        if (allFields.getMediumList().size() == 0) {
            Log.i(getClass().getSimpleName(), "No medium found. Creating default.");
            ExpenseMedium medium = new ExpenseMedium();
            medium.setName("Unknown");
            medium.setTimestamp(new Date());
            medium.setDetails("Default medium created by App");
            int mID = (int) appData.mediums().addMedium(medium);
            medium.setId(mID);
            allFields.getMediumList().add(medium);
        }
        dataReceived.dataReceived(allFields);
        return null;
    }

    public interface OnDataReceived {
        void dataReceived(AllFields af);
    }
}
