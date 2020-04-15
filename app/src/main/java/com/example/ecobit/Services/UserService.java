package com.example.ecobit.Services;

import com.example.ecobit.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("login")
    Call<User> user(@Query("userId") String userId);


    @POST("login")
    Call<User> login(@Body User user);

    @POST("registro")
    Call<User> registro(@Body User user);

    @POST("user/updatePhoto")
    Call<User> postPhoto(@Body User user);

    @HTTP(method = "DELETE", path = "/api/v1/user", hasBody = true)
    Call<User> bajaUser(@Body User user);


}
