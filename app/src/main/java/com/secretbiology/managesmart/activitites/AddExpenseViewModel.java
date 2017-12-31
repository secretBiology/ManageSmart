package com.secretbiology.managesmart.activitites;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.common.GetAllExpenseData;


public class AddExpenseViewModel extends ViewModel {

    private MutableLiveData<AllFields> allFields = new MutableLiveData<>();

    public MutableLiveData<AllFields> getAllFields() {
        return allFields;
    }

    public void retrieveData(Context context) {
        new GetAllExpenseData(context, new GetAllExpenseData.OnDataReceived() {
            @Override
            public void dataReceived(AllFields af) {
                allFields.postValue(af);
            }
        }).execute();
    }
}
