package com.example.firsttryapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> selectedUsername = new MutableLiveData<String>();
    private final MutableLiveData<String> selectedId = new MutableLiveData<String>();

    public void setSelectedUsername(String item){
        selectedUsername.setValue(item);
    }
    public void setSelectedId(String item){
        selectedId.setValue(item);
    }

    public LiveData<String> getSelectedUsername(){
        return selectedUsername;
    }

    public LiveData<String> getSelectedId(){
        return selectedId;
    }
}
