package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;

import java.io.Serializable;

public class PpalUsuarioActivity extends AppCompatActivity {

   TextView tvNombre;
   String nombreCompleto;
    //  //tvSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User) getIntent().getSerializableExtra("usuario");
        setContentView(R.layout.activity_ppal_usuario);

        tvNombre = (TextView)  findViewById(R.id.textViewNombre);
        //tvSaldo =  (TextView)  findViewById(R.id.textViewSaldo);

       tvNombre.setText (user.getNombre().toString());
    //    tvSaldo.setText (user.getPassword().toString());

        nombreCompleto = user.getNombre()+ " "+user.getApellido();

        Toast.makeText(PpalUsuarioActivity.this, "Bienvenido "+nombreCompleto, Toast.LENGTH_SHORT).show();
    }

    //METODO IrMenu
    public void IrMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}
