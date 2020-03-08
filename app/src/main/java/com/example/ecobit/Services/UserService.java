package com.example.ecobit.Services;

import com.example.ecobit.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("api/ecobit/user")
    Call<User> user(@Query("userId") String userId);

    @GET("api/ecobit/user")
    Call<List<User>> getUsers();

    @POST("api/ecobit/login")
    Call<User> login(@Query("email") String email,@Query("pass") String pass);

    @POST("api/ecobit/signIn")
    Call<User> signIn(
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("nombre") String nombre,
            @Query("apellido") String apellido,
            @Query("tipo_doc") String tipo_doc,
            @Query("nro_documento") String nro_documento,
            @Query("fecha_nac") String fecha_nac,
            @Query("tel") String tel,
            @Query("foto") String foto);
}
