package com.example.fitness.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.ModelExercise;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import java.util.List;

public class ViewModelExercise extends ViewModel {

    public MutableLiveData<List<ModelExercise>> mutableLiveDataPost = new MutableLiveData<>();

    public void Exercise() {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().Exercise(), object -> mutableLiveDataPost.setValue((List) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
