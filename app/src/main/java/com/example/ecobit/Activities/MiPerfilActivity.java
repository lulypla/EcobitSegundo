package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.utils.Sesion;

public class MiPerfilActivity extends AppCompatActivity {

    TextView tvNombre;
    TextView tvCorreo;
    TextView tvEdad;
    TextView tvCel;

    private void setTextViews() {
        tvNombre = findViewById(R.id.tvUserName);
        tvCorreo = findViewById(R.id.tvUserMail);
        tvEdad = findViewById(R.id.tvUserAge);
        tvCel = findViewById(R.id.tvUserCel);
    }

    private void setTextViewsValues(User user) {
        tvNombre.setText(user.getNombre() + " " + user.getApellido());
        tvCorreo.setText(user.getEmail());
        tvEdad.setText(user.getFecha_nac());
        tvCel.setText(user.getTel());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final User user = Sesion.getUser(this);
        setContentView(R.layout.activity_mi_perfil);
        this.setTextViews();
        this.setTextViewsValues(user);
    }

    //METODO IrMenu
    public void IrMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrEditarPerfil
    public void IrEditarPerfil(View view) {
        Intent irEditarPerfil = new Intent(this, EditarPerfilActivity.class);
        startActivity(irEditarPerfil);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}
