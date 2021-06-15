package com.example.fitness.utils;


import io.reactivex.disposables.CompositeDisposable;

public final class SingleTon {

    public static CompositeDisposable INSTANCE;
    public static CompositeDisposable com(){
        if (INSTANCE==null){
            INSTANCE=new CompositeDisposable();
        }
        return INSTANCE;
    }

}
