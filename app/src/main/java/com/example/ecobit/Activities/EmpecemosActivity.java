package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.ecobit.R;

public class EmpecemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empecemos);

    }
        //METODO Irlogin
        public void Irlogin(View view){
            Intent irlogin = new Intent(this, LoginActivity.class);
            startActivity(irlogin);
            overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        }



}
