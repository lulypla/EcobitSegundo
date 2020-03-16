package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobit.R;

public class PpalUsuarioActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppal_usuario);
    }

    //METODO IrMenu
    public void IrMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}
