package com.example.world_resources_app;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/getQuiz")
    Call<ScoreResult> getQuiz (@Body HashMap<String,String> map);
    @POST("/storeQuiz")
    Call<Void> storeQuiz (@Body HashMap<String,String> map);
}
