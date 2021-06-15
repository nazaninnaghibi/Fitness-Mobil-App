package com.example.fitness.viewmodel;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fitness.R;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.utils.SingleTon;

import es.dmoral.toasty.Toasty;

public class ViewModelLogin extends AndroidViewModel {
    public MutableLiveData<Status> mutableLiveDataLogin = new MutableLiveData<>();
    public MutableLiveData<Boolean> mutableLiveDataRegister = new MutableLiveData<>();
    public String username = null;
    public String password = null;
    Application application;

    public ViewModelLogin(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void Login(View view) {
        if (username == null) {
            Toasty.error(application.getApplicationContext(), R.string.enter_username, Toast.LENGTH_SHORT, true).show();
        } else if (password == null) {
            Toasty.error(application.getApplicationContext(), R.string.enter_password, Toast.LENGTH_SHORT, true).show();
        } else {
            Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().Login(username, password), object -> mutableLiveDataLogin.setValue((Status) object));
        }
    }

    public void Register(View view) {
        mutableLiveDataRegister.setValue(true);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }


}
