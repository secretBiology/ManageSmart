package com.secretbiology.managesmart.common;

import android.content.Context;
import android.os.AsyncTask;

import com.secretbiology.helpers.general.Log;
import com.secretbiology.managesmart.database.AppData;
import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.Date;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

public class CreateDefaultValues extends AsyncTask<Void, Void, Void> {

    private AppData appData;
    private OnFinish onFinish;

    public CreateDefaultValues(Context context, OnFinish onFinish) {
        this.appData = AppData.getDatabase(context);
        this.onFinish = onFinish;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AllFields af = new AllFields();
        Log.inform("Deleting all categories and subcategories.");
        appData.categories().deleteAll();
        Log.inform("All categories and subcategories deleted.");
        addCat("Food");
        addCat("Transportation");
        addCat("Shopping");
        addCat("Vehicle");
        addCat("Education");
        addCat("Medical");
        addCat("Bills");
        addCat("Banks");
        addCat("Miscellaneous");
        int unkID = addCat("Unknown");
        appData.entries().updateAllCats(unkID);
        appData.entries().allModified(new Date());
        Log.inform("All entries category changed to default");
        if (appData.mediums().getAllMediums().size() == 0) {
            addMedium("Cash");
            addMedium("Debit Card");
            addMedium("Credit Card");
            addMedium("Internet Banking");
            addMedium("Wallet");
            addMedium("Unknown");
        }

        af.setCategoryList(appData.categories().getAllCategories());
        af.setMediumList(appData.mediums().getAllMediums());
        af.setEntryList(appData.entries().getAllEntries());
        onFinish.addedDefaultValues(af);

        return null;
    }

    private int addCat(String CategoryName) {
        ExpenseCategory category = new ExpenseCategory();
        category.setName(CategoryName);
        category.setTimestamp(new Date());
        category.setDetails("Default category created by App");
        int catID = (int) appData.categories().addCategory(category);
        Log.inform("Default category created: " + CategoryName);
        return catID;
    }

    private void addMedium(String name) {
        ExpenseMedium medium = new ExpenseMedium();
        medium.setName(name);
        medium.setTimestamp(new Date());
        medium.setDetails("Default medium created by App");
        appData.mediums().addMedium(medium);
    }

    public interface OnFinish {
        void addedDefaultValues(AllFields allFields);
    }
}
