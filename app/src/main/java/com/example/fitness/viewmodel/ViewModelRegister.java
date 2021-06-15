package com.example.fitness.viewmodel;

import android.annotation.SuppressLint;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


@SuppressLint("StaticFieldLeak")
public class ViewModelRegister extends AndroidViewModel {
    public MutableLiveData<Status> mutableLiveDataRegister = new MutableLiveData<>();
    public String username = null;
    public String email = null;
    public String password = null;
    Application application;

    public ViewModelRegister(Application application) {
        super(application);
        this.application = application;
    }

    public void Register(View view) {
        if (username == null || username.isEmpty()) {
            Toasty.error(application.getApplicationContext(), R.string.enter_username, Toast.LENGTH_SHORT, true).show();
        } else if (email == null || email.isEmpty()) {
            Toasty.error(application.getApplicationContext(), R.string.enter_email, Toast.LENGTH_SHORT, true).show();
        } else if (!isEmailValid(email)) {
            Toasty.error(application.getApplicationContext(), R.string.valid_email, Toast.LENGTH_SHORT, true).show();
        } else if (password == null || password.isEmpty()) {
            Toasty.error(application.getApplicationContext(), R.string.enter_password, Toast.LENGTH_SHORT, true).show();
        } else {
            Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().Register(username, email, password), object -> mutableLiveDataRegister.setValue((Status) object));
        }


    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        SingleTon.com().clear();
    }
}
