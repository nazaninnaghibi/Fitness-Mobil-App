package com.example.fitness.utils;

import com.example.fitness.model.Following;
import com.example.fitness.model.ModelExercise;
import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.model.ModelFitnessDietDetails;
import com.example.fitness.model.ModelShowAllUser;
import com.example.fitness.model.ModelUserInfo;
import com.example.fitness.model.Status;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    Api.Companion Compation = Companion.$$Instance;

    @FormUrlEncoded
    @POST("register.php")
    Single<Status> Register(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("accountdetails.php")
    Single<Status> accountdetails(@Field("weight") int weight, @Field("height") int height, @Field("birthday") int birthday, @Field("gender") String gender, @Field("token") String token, @Field("description") String description);


    @FormUrlEncoded
    @POST("info.php")
    Single<List<ModelUserInfo>> UserInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST("login.php")
    Single<Status> Login(@Field("username") String username, @Field("password") String password);

    @GET("Fitnessdiet.php")
    Single<List<ModelFitnessDiet>> Fitnessdiet();


    @FormUrlEncoded
    @POST("Fitnessdietdetails.php")
    Single<ModelFitnessDietDetails> ModelFitnessDietDetails(@Field("idFitnessdiet") String idFitnessdiet);

    @FormUrlEncoded
    @POST("AddFavorite.php")
    Single<Status> ModelAddFavorite(@Field("id") String id, @Field("token") String token);

    @FormUrlEncoded
    @POST("CheckFavorite.php")
    Single<Status> CheckFavorite(@Field("id") String id, @Field("token") String token);

    @FormUrlEncoded
    @POST("ShowFavorite.php")
    Single<List<ModelFitnessDiet>> ShowFavorite(@Field("token") String token);

    @FormUrlEncoded
    @POST("ShowFavoriteLatest.php")
    Single<List<ModelFitnessDiet>> ShowFavoriteLimit(@Field("token") String token);


    @FormUrlEncoded
    @POST("DeleteFavorite.php")
    Single<Status> DeleteFavorite(@Field("id") String id);


    @GET("Exercise.php")
    Single<List<ModelExercise>> Exercise();


    @FormUrlEncoded
    @POST("ExerciseOption.php")
    Single<List<ModelExerciseOptions>> ExerciseOptions(@Field("idexercise") String idexercise);


    @FormUrlEncoded
    @POST("ExerciseDetails.php")
    Single<ModelFitnessDietDetails> ExerciseDetails(@Field("idexercise") String idexercise, @Field("idexerciseoption") String idexerciseoption);


    @FormUrlEncoded
    @POST("AddFavoriteExercise.php")
    Single<Status> AddFavoriteExercise(@Field("idexercise") String idexercise, @Field("idexerciseoption") String idexerciseoption, @Field("token") String token);

    @FormUrlEncoded
    @POST("CheckFavoriteExercise.php")
    Single<Status> CheckFavoriteExercise(@Field("idexerciseoption") String idexerciseoption, @Field("token") String token);

    @FormUrlEncoded
    @POST("DeleteFavoriteExercise.php")
    Single<Status> DeleteFavoriteExercise(@Field("id") String id);

    @FormUrlEncoded
    @POST("ShowFavoriteExercise.php")
    Single<List<ModelExerciseOptions>> ShowFavoriteExercise(@Field("token") String token);


    @FormUrlEncoded
    @POST("ShowAllUser.php")
    Single<List<ModelShowAllUser>> ShowAllUser(@Field("token") String token);

    @FormUrlEncoded
    @POST("Follow.php")
    Single<Status> Follow(@Field("CurrentToken") String CurrentToke, @Field("Token") String Token);

    @FormUrlEncoded
    @POST("CheckFollow.php")
    Single<Status> CheckFollow(@Field("CurrentToken") String CurrentToke, @Field("Token") String Token);


    @FormUrlEncoded
    @POST("Count.php")
    Single<Following> Following(@Field("CurrentToken") String CurrentToke);

    @FormUrlEncoded
    @POST("ShowFollowing.php")
    Single<List<ModelShowAllUser>> ShowFollowing(@Field("token") String token);


    final class Companion {
        static final Api.Companion $$Instance;

        public final Api invoke() {

            return (new Retrofit.Builder())
                    .baseUrl("https://salmandhealth.ir/fitness/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api.class);
        }

        static {
            $$Instance = new Companion();
        }

    }
}
