package com.example.ecobit.Services;

import android.widget.EditText;

import com.example.ecobit.Model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UserService {
    @GET("login")
    Call<User> user(@Query("userId") String userId);

    @GET("api/ecobit/user")
    Call<List<User>> getUsers();

    //@POST("login")
   // Call<User> login(@Query("email") String email,@Query("pass") String pass);

    @POST("login")
    Call<User> login(@Body User user);

    @POST("registro")
    Call<User> registro(@Body User user);

    @POST("registro")
    Call<User> signI2(
            @Field("email") EditText email,
            @Field("password") EditText pass,
            @Field("nombre") EditText nombre,
            @Field("apellido") EditText apellido,
            @Field("tel") EditText tel,
            @Field("nro_doc") String nro_documento,
            @Field("tipo_doc") String tipo_doc,
            @Field("fecha_nac") String fecha_nac);

}
