package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;
import com.example.ecobit.utils.Sesion;

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

        final EditText emailEdit =  findViewById(R.id.editTextEmail);
        final EditText passwordEdit = findViewById(R.id.editTextPass);
        String email = emailEdit.getText().toString();
        String password =  passwordEdit.getText().toString();

        // No formularios vacios
        if(email.length() == 0 ||password.length() == 0 ){
            Toast.makeText(LoginActivity.this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog spinner = ProgressDialog.show(LoginActivity.this, "Iniciando sesion",
                "Enviando datos, por favor espere...", true);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        UserService userService = APIService.getApi().create(UserService.class);
        Call<User> userLogged = userService.login(user);
        final Intent iPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
        final Activity activity = this;
        userLogged.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getEmail() != null) {
                    Sesion.setUser(user, activity);
                    iPpalUsuario.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(iPpalUsuario);
                    overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                    spinner.cancel();
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Error contraseña o email incorrectos", Toast.LENGTH_LONG).show();
                    spinner.cancel();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                spinner.cancel();
                Toast.makeText(LoginActivity.this, "Error en Pedido, Compruebe su conexion a internet", Toast.LENGTH_LONG).show();
            }
        });

    }
}



