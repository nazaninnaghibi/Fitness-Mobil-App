package com.example.fitness.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import java.util.List;

public class ViewModelShowFavoriteLimit extends ViewModel {

    public MutableLiveData<List<ModelFitnessDiet>> mutableLiveDataPost = new MutableLiveData<>();

    public void ShowFavorite(String token) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().ShowFavoriteLimit(token), object -> mutableLiveDataPost.setValue((List) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
