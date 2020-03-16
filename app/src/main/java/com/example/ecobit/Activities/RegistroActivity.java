package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

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
    private EditText textNombre;
    private EditText textApellido;
    private EditText textEmail;
    private EditText textCel;
    private EditText textPass;
    private EditText textPass2;

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

    // volver a el activity de Emprecemos
    public void VolverAEmpecemos(View view){
        Intent intent = new Intent(this, EmpecemosActivity.class);
        startActivity(intent);
    }
    // Registro
    public void Registro(View view){
        // los inputs
        textNombre = (EditText)findViewById(R.id.editNombre);
        textApellido = (EditText)findViewById(R.id.editApellido);
        textEmail = (EditText)findViewById(R.id.editEmail);
        textCel = (EditText)findViewById(R.id.editCelular);
        textPass = (EditText)findViewById(R.id.editPass);
        textPass2 = (EditText)findViewById(R.id.editPass2);
        if(ComprobarCampos(textNombre, textApellido, textCel, textEmail, textPass, textPass2)){
            if(textPass.toString() == textPass2.toString()){
                // acá iría el insert
                Toast.makeText(this, "Registro en proceso....", Toast.LENGTH_SHORT).show();
            }

        }
        
    }
    // compruba que los campos de registro no esten vacios
    public boolean ComprobarCampos(EditText nombre, EditText apellido, EditText cel, EditText email, EditText pass, EditText pass2){
        if(!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty()
                && !cel.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                && !pass.getText().toString().isEmpty() && !pass2.getText().toString().isEmpty()
        ){
            if(textPass.getText().toString().equals(textPass2.getText().toString())){
                Toast.makeText(this, "Procesando su registro...", Toast.LENGTH_SHORT).show();
                return true;
            }
            else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "Ingresa la información en todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
