package com.example.fitness.viewmodel;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fitness.R;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import es.dmoral.toasty.Toasty;

public class ViewModelUpdateProfile extends AndroidViewModel {
    public MutableLiveData<Status> mutableLiveDataUpdate = new MutableLiveData<>();
    public int weight;
    public int height;
    public int date_of_birth;
    public String gender;
    public String description;
    public String token;
    Application application;

    public ViewModelUpdateProfile(Application application) {
        super(application);
        this.application = application;
    }

    public void Update(View view) {
        if (gender.isEmpty()) {
            Toasty.error(application.getApplicationContext(), R.string.enter_gender, Toast.LENGTH_SHORT, true).show();
        } else if (description.isEmpty()) {
            Toasty.error(application.getApplicationContext(), R.string.enter_description, Toast.LENGTH_SHORT, true).show();
        } else {
            Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().accountdetails(weight, height, date_of_birth, gender, token, description), object -> mutableLiveDataUpdate.setValue((Status) object));
        }


    }


    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
