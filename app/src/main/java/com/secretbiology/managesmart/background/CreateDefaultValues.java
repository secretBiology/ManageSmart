package com.secretbiology.managesmart.background;

import android.content.Context;
import android.os.AsyncTask;

import com.secretbiology.helpers.general.Log;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.database.AppData;
import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;
import com.secretbiology.managesmart.database.ExpenseSubCategory;

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
        Log.inform("Deleting all categories and subcategories.");
        appData.categories().deleteAll();
        appData.subCategories().deleteAll();
        Log.inform("All categories and subcategories deleted.");
        addCat("Food", "Breakfast", "Lunch", "Dinner", "Snacks", "Coffee", "Drinks", "Dessert", "Other");
        addCat("Transportation", "Bus", "Train", "Air", "Cab", "Metro", "Tram", "Other");
        addCat("Shopping", "Appliances", "Vegetables", "Cloths", "Gift", "Online", "Other");
        addCat("Vehicle", "Fuel", "Repair", "Insurance", "Other");
        addCat("Education", "Fees", "Stationary", "Field Trip", "Loan", "Other");
        addCat("Medical", "Medicine", "Hospital OPD", "Surgery", "Other");
        addCat("Bills", "Electricity", "Water", "Insurance", "Cell Phone", "Rent", "Other");
        addCat("Banks", "Cash Withdraw", "Deposit", "Investment", "Loan", "Fees", "Other");
        addCat("Miscellaneous", "Home Repair", "Contribution", "Donation", "Other");
        int unkID = addCat("Unknown");
        appData.entries().updateAllCats(unkID, 0);
        appData.entries().allModified(new Date());
        Log.inform("All entries category changed to default");
        addMedium("Cash");
        addMedium("Debit Card");
        addMedium("Credit Card");
        addMedium("Internet Banking");
        addMedium("Wallet");
        addMedium("Unknown");
        return null;
    }

    private int addCat(String CategoryName, String... subCats) {
        ExpenseCategory category = new ExpenseCategory();
        category.setName(CategoryName);
        category.setTimestamp(new Date());
        category.setDetails("Default category created by App");
        int catID = (int) appData.categories().addCategory(category);

        for (String s : subCats) {
            ExpenseSubCategory subCategory = new ExpenseSubCategory();
            subCategory.setCategory(catID);
            subCategory.setName(s);
            subCategory.setTimestamp(new Date());
            subCategory.setDetails("Default sub-category created by App");
            appData.subCategories().addSubCategory(subCategory);
        }
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
