package com.example.ecobit.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;
import com.example.ecobit.utils.Sesion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCuentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
    }

    //METODO IrMenu
    public void IrMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void cerrarSesion(View view) {
        Intent logout = new Intent(this, EmpecemosActivity.class);
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Sesion.borrar(this);
        startActivity(logout);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void darseDeBaja(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmar baja");
        builder.setMessage("Esta seguro que quiere darse de baja?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestBaja();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void requestBaja() {
        final ProgressDialog spinner = ProgressDialog.show(MiCuentaActivity.this, "Procesando baja",
                "Enviando datos, por favor espere...", true);
        User user = Sesion.getUser(MiCuentaActivity.this);
        UserService userService = APIService.getApi().create(UserService.class);
        Call<User> userBaja = userService.bajaUser(user);
        final Intent intentEmpecemos = new Intent(this, EmpecemosActivity.class);
        userBaja.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getEmail() != null) {
                    intentEmpecemos.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentEmpecemos);
                    overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                    spinner.cancel();
                    Toast.makeText(MiCuentaActivity.this, "Usuario dado de baja con exito", Toast.LENGTH_LONG).show();

                    finish();

                } else {
                    Toast.makeText(MiCuentaActivity.this, "Error en la baja", Toast.LENGTH_LONG).show();
                    spinner.cancel();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                spinner.cancel();
                Toast.makeText(MiCuentaActivity.this, "Error en Pedido, Compruebe su conexion a internet", Toast.LENGTH_LONG).show();
            }
        });
    }


}
