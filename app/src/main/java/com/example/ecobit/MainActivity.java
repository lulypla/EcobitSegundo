package com.example.ecobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecobit.Activities.Intro2Activity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }

     //METODO Next para  btnnext
    public void Next(View view){
        startActivity(new Intent(this, Intro2Activity.class));
        overridePendingTransition(R.anim.right_in, R.anim.rigth_out);

    }
}
