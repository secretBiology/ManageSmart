package com.secretbiology.managesmart.activities;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.database.AppData;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

public class AddExpenseViewModel extends ViewModel {

    MutableLiveData<AllFields> allFields = new MutableLiveData<>();

    public MutableLiveData<AllFields> getAllFields() {
        return allFields;
    }

    public void extractFields(Context context) {
        new ExtractInfo(context, new ExtractInfo.onFinish() {
            @Override
            public void data(AllFields fields) {
                allFields.postValue(fields);
            }
        }).execute();
    }


    static class ExtractInfo extends AsyncTask<Void, Void, Void> {

        private onFinish finish;
        private AppData appData;

        ExtractInfo(Context context, onFinish finish) {
            this.finish = finish;
            this.appData = AppData.getDatabase(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AllFields fields = new AllFields();
            fields.setCategoryList(appData.categories().getAllCategories());
            fields.setSubCategoryList(appData.subCategories().getAllSubCategories());
            fields.setMediumList(appData.mediums().getAllMediums());
            finish.data(fields);
            return null;
        }

        interface onFinish {
            void data(AllFields fields);
        }
    }
}
