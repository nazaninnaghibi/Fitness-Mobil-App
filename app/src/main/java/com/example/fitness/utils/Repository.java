package com.example.fitness.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    public static final Repository INSTACNCE;

    public final void CustomResponse(Single api, final Unit unit) {
        SingleTon.com().add((Disposable)
                api.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver() {
                            @Override
                            public void onSuccess(@NonNull Object o) {
                                unit.invoke(o);
                            }

                            @Override
                            public void onError( @NonNull Throwable e) {

                            }
                        }));

    }

    static {
        INSTACNCE = new Repository();
    }

    public interface Unit {
        void invoke(Object object);
    }

    public static void Shared_write(Context context, String token){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("token",token);
        editor.apply();
    }

    public static String Shared_Read(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
        return sharedPreferences.getString("token",null);
    }

    public static void Shared_exit(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
