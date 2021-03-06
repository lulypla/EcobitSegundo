package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.GestureDescription;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    // declaramos los campos de registro
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText tel;
    private EditText password;
    private EditText textPass2;
    String fecha_nac;
    String nro_doc;
    String tipo_doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }


    // volver a el activity de Emprecemos
    public void VolverAEmpecemos(View view) {
        Intent intent = new Intent(this, EmpecemosActivity.class);
        startActivity(intent);
    }

    // Registro
    public void Registro(View view) {
        // los inputs
        nombre = (EditText) findViewById(R.id.editNombre);
        apellido = (EditText) findViewById(R.id.editApellido);
        email = (EditText) findViewById(R.id.editEmail);
        tel = (EditText) findViewById(R.id.editCelular);
        password = (EditText) findViewById(R.id.editPass);
        textPass2 = (EditText) findViewById(R.id.editPass2);
        if (ComprobarCampos(nombre, apellido, tel, email, password, textPass2)) {
            //if(textPass.toString() == textPass2.toString()) {
            // acá iría el insert
            User user = new User(
                    email.getText().toString(),
                    password.getText().toString(),
                    nombre.getText().toString(),
                    apellido.getText().toString(),
                    null,
                    null,
                    null,
                    tel.getText().toString(),
                    null,
                    null);
            Insert(user);

        }
    }

    public void Insert(User user) {
        final ProgressDialog spinner = ProgressDialog.show(this, "Creando usuario",
                "Enviando datos, por favor espere...", true);
        UserService userService = APIService.getApi().create(UserService.class);
        Call<User> userRegistrado = userService.registro(user);
        userRegistrado.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getEmail() != null) {
                    spinner.cancel();
                    Toast.makeText(RegistroActivity.this, "Registro realizado con éxito", Toast.LENGTH_LONG).show();
                    final Intent alLogin = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(alLogin);
                }
                spinner.cancel();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                spinner.cancel();
                Toast.makeText(RegistroActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }


    // compruba que los campos de registro no esten vacios
    public boolean ComprobarCampos(EditText nombre, EditText apellido, EditText cel, EditText email, EditText pass, EditText pass2) {
        if (!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty()
                && !cel.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                && !pass.getText().toString().isEmpty() && !pass2.getText().toString().isEmpty()
        ) {
            if (password.getText().toString().equals(textPass2.getText().toString())) {
                return true;
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
            Toast.makeText(this, "Ingresa la información en todos los campos", Toast.LENGTH_LONG).show();
            return false;
    }
}
