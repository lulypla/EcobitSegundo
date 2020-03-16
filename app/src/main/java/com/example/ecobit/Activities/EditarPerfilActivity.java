package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobit.Activities.MenuActivity;
import com.example.ecobit.R;

public class EditarPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
    }

    public void IrPpalUsuario(View view) {
        Intent irPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
        startActivity(irPpalUsuario);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}
