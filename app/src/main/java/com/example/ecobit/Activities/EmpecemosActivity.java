package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecobit.R;

public class EmpecemosActivity extends AppCompatActivity {

    // declaramos el botón de inicio de sesión y el de registro
    private Button BtnInicioSesion;
    private Button BtnRegistro ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empecemos);


        // definimos el botón de inicio de sesión y de registro
        BtnInicioSesion = findViewById(R.id.inicio);
        BtnRegistro = findViewById(R.id.registro);

        // acción botón de inicio de sesión
        BtnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmpecemosActivity.this, "Test Inicio de sesión",Toast.LENGTH_LONG).show();
            }
        });

        // acción botón registro
        BtnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmpecemosActivity.this, "Test Registro",Toast.LENGTH_LONG).show();

            }
        });



    }
}
