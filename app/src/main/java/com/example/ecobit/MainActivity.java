package com.example.ecobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ecobit.Activities.Intro2Activity;
import com.example.ecobit.Activities.PpalUsuarioActivity;
import com.example.ecobit.Model.User;
import com.example.ecobit.utils.Sesion;

public class MainActivity extends AppCompatActivity {


    TextView tvNombre;
    TextView tvSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = Sesion.getUser(this);
        if(user != null) {
            startActivity(new Intent(this, PpalUsuarioActivity.class));
        } else {
            setContentView(R.layout.activity_intro);
        }
     }

     //METODO Next para  btnnext
    public void Next(View view){
        startActivity(new Intent(this, Intro2Activity.class));
        overridePendingTransition(R.anim.right_in, R.anim.rigth_out);

    }

}
