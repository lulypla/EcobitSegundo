package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
        //METODO PpalUsuario
        public void PpalUsuaro(View view) {
            Intent iPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
            startActivity(iPpalUsuario);
            overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);

        }}
        /*UserService userService = APIService.getApi().create(UserService.class);

        Call<User> userLogged =  userService.login("lucia","1234");

        userLogged.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        });*/


