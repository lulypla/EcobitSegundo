package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        UserService userService = APIService.getApi().create(UserService.class);

        Call<User> userRegistiry =  userService.signIn("lucia@ecobit.com", "1234", "lucia", "nassutti", "CI","123456789", "28/03/1989", "09837", "");

        userRegistiry.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegistroActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
