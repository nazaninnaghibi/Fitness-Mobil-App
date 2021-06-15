package com.example.fitness.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.ModelShowAllUser;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import java.util.List;

public class ViewModelShowAllUser extends ViewModel {

    public MutableLiveData<List<ModelShowAllUser>> mutableLiveDataPost = new MutableLiveData<>();

    public void User(String token) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().ShowAllUser(token), object -> mutableLiveDataPost.setValue((List) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
