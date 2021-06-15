package com.example.fitness.viewmodel;



import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.Following;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

public class ViewModelFollowing extends ViewModel {
    public MutableLiveData<Following> mutableLiveData = new MutableLiveData<>();


    public void Following(String CurrentToken) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().Following(CurrentToken), object -> mutableLiveData.setValue((Following) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
