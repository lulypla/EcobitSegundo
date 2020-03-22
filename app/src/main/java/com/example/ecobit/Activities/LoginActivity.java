package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

 private String nombrePrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(LoginActivity.this, "Ingrese su usuario y contraseña", Toast.LENGTH_LONG).show();
    }

    //METODO PpalUsuario
    public void PpalUsuaro(View view) {

        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText password = (EditText) findViewById(R.id.editTextPass);
        User user = new User(
                email.getText().toString(),
                password.getText().toString(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        UserService userService = APIService.getApi().create(UserService.class);
        Call<User> userLogged = userService.login(user);
        final Intent iPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
        userLogged.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getEmail() != null) {
                    iPpalUsuario.putExtra("usuario", user);
                    startActivity(iPpalUsuario);
                    overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                } else {
                    Toast.makeText(LoginActivity.this, "Error contraseña o email incorrectos", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error en Pedido, Compruebe su conexion a internet", Toast.LENGTH_LONG).show();
            }
        });

    }
}



