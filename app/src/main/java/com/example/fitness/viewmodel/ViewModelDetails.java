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

public class ViewModelDetails extends AndroidViewModel {
    public MutableLiveData<Status> mutableLiveDataUpdate = new MutableLiveData<>();
    public Integer weight;
    public Integer height;
    public Integer date_of_birth;
    public String gender = null;
    public String token = null;
    public String description = null;
    Application application;

    public ViewModelDetails(Application application) {
        super(application);

        this.application = application;
    }

    public void Update(View view) {

        if (date_of_birth == null) {
            Toasty.error(application.getApplicationContext(), R.string.enter_date_of_birth, Toast.LENGTH_SHORT, true).show();
        } else if (weight == null) {
            Toasty.error(application.getApplicationContext(), R.string.enter_weight, Toast.LENGTH_SHORT, true).show();
        } else if (height == null) {
            Toasty.error(application.getApplicationContext(), R.string.enter_height, Toast.LENGTH_SHORT, true).show();
        } else if (gender == null) {
            Toasty.error(application.getApplicationContext(), R.string.enter_gender, Toast.LENGTH_SHORT, true).show();
        } else if (description == null) {
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
