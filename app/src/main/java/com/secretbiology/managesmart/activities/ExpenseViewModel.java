package com.secretbiology.managesmart.activities;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.secretbiology.helpers.general.Log;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.common.CreateDefaultValues;
import com.secretbiology.managesmart.database.AppData;
import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.Date;
import java.util.List;


public class ExpenseViewModel extends ViewModel {

    private MutableLiveData<AllFields> allFields = new MutableLiveData<>();
    private MutableLiveData<ExpenseCategory> category = new MutableLiveData<>();

    public MutableLiveData<AllFields> getAllFields() {
        return allFields;
    }

    public MutableLiveData<ExpenseCategory> getCategory() {
        return category;
    }

    public void retrieveAllInformation(final Context context) {
        new GetInfo(context, new taskFinished() {
            @Override
            public void retrieved(AllFields af) {
                allFields.postValue(af);
            }

            @Override
            public void makeDefaultCat() {

                startMakingCategories(context);

            }
        }).execute();
    }

    private void startMakingCategories(Context context) {

        new CreateDefaultValues(context, new CreateDefaultValues.OnFinish() {
            @Override
            public void addedDefaultValues(AllFields af) {
                allFields.postValue(af);
            }
        }).execute();

    }

    public void addCategory(Context context, String name) {
        new AddCategory(context, name, new categoryAdded() {
            @Override
            public void successful(ExpenseCategory c) {
                category.postValue(c);
            }

            @Override
            public void alreadyExists() {
                ExpenseCategory c = new ExpenseCategory();
                c.setId(0);
                category.postValue(c);
            }
        }).execute();
    }

    private static class AddCategory extends AsyncTask<Void, Void, Void> {

        private categoryAdded added;
        private AppData appData;
        private String name;

        AddCategory(Context context, String catName, categoryAdded added) {
            this.added = added;
            this.name = catName;
            this.appData = AppData.getDatabase(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.inform("Checking if category exists");
            long catID = appData.categories().isAlreadyThere(name);
            if (catID != 0) {
                added.alreadyExists();
            } else {
                ExpenseCategory category = new ExpenseCategory();
                category.setName(name);
                category.setTimestamp(new Date());
                category.setDetails("Created by user");
                long id = appData.categories().addCategory(category);
                category.setId((int) id);
                added.successful(category);
            }
            return null;
        }
    }


    private static class GetInfo extends AsyncTask<Void, Void, Void> {

        private taskFinished information;
        private AppData appData;

        GetInfo(Context context, taskFinished information) {
            this.information = information;
            this.appData = AppData.getDatabase(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            AllFields fields = new AllFields();
            fields.setCategoryList(appData.categories().getAllCategories());
            List<ExpenseMedium> medList = appData.mediums().getAllMediums();
            if (medList.size() > 0) {
                fields.setMediumList(medList);
                fields.setCurrentMedium(medList.get(0));
            } else {
                Log.inform("No mediums found. Creating default.");
                ExpenseMedium medium = new ExpenseMedium();
                medium.setName("Unknown");
                medium.setTimestamp(new Date());
                medium.setDetails("Default medium created by App");
                appData.mediums().addMedium(medium);
                fields.setMediumList(appData.mediums().getAllMediums());
                fields.setCurrentMedium(fields.getMediumList().get(0));
            }

            if (fields.getCategoryList().size() != 0) {
                information.retrieved(fields);
            } else {
                Log.inform("No categories found in the database.");
                information.makeDefaultCat();
            }
            return null;
        }
    }

    private interface categoryAdded {
        void successful(ExpenseCategory category);

        void alreadyExists();
    }

    private interface taskFinished {

        void retrieved(AllFields allFields);

        void makeDefaultCat();

    }
}
