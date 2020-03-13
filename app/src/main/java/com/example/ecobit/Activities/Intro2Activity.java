package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobit.R;

public class Intro2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);
    }

    //METODO Next para  btnnext2
    public void Next(View view){
        Intent next = new Intent(this, Intro3Activity.class);
        startActivity(next);
        overridePendingTransition(R.anim.right_in, R.anim.rigth_out);
    }
}
