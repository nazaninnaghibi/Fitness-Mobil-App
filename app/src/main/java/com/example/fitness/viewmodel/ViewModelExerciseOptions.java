package com.example.fitness.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import java.util.List;

public class ViewModelExerciseOptions extends ViewModel {

    public MutableLiveData<List<ModelExerciseOptions>> mutableLiveDataPost = new MutableLiveData<>();

    public void ExerciseOptions(String idexercise) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().ExerciseOptions(idexercise), object -> mutableLiveDataPost.setValue((List) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
