package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobit.R;

public class Intro3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro3);
    }
    //METODO Next para  empecemos
    public void Empecemos(View view){
        Intent empecemos = new Intent(this, EmpecemosActivity.class);
        startActivity(empecemos);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

}
