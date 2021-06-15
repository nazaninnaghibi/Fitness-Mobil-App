package com.example.fitness.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitness.model.ModelFitnessDietDetails;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

public class ViewModelExerciseDetails extends ViewModel {
    public MutableLiveData<ModelFitnessDietDetails> mutableLiveDataPost = new MutableLiveData<>();

    public void ModelFitnessExerciseDetails(String idexercise, String idexerciseoption) {
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().ExerciseDetails(idexercise, idexerciseoption), object -> mutableLiveDataPost.setValue((ModelFitnessDietDetails) object));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
