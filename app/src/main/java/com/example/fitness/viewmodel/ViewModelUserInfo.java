package com.example.fitness.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.ModelUserInfo;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import java.util.List;

public class ViewModelUserInfo extends ViewModel {

    public MutableLiveData<List<ModelUserInfo>> mutableinfo = new MutableLiveData<>();

    public void UserInfo(String token) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().UserInfo(token), object -> mutableinfo.setValue((List) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
