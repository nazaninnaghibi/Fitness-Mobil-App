package com.example.fitness.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.Status;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;


public class ViewModelAddFavorite extends ViewModel {

    public MutableLiveData<Status> mutableLiveData = new MutableLiveData<>();
    public String id = null;
    public String token = null;

    public void AddFavorite(View view) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().ModelAddFavorite(id, token), object -> mutableLiveData.setValue((Status) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
