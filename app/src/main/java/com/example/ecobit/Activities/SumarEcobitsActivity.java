package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SumarEcobitsActivity extends AppCompatActivity {

    String nombreCompleto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User) getIntent().getSerializableExtra("usuario");
        setContentView(R.layout.activity_sumar_ecobits);
        configureButtonReader();

        nombreCompleto = user.getNombre() + " " + user.getApellido();
    }

    private void configureButtonReader() {
        final ImageButton buttonReader = (ImageButton)findViewById(R.id.imageButtonSumarEcobits);
        buttonReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(SumarEcobitsActivity.this).initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        handleResult(scanResult);
    }

    private void handleResult(IntentResult scanResult) {
        if (scanResult != null) {
            updateUITextViews(scanResult.getContents(), scanResult.getFormatName());
        } else {
            Toast.makeText(this, "No se ha leído nada :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUITextViews(String scan_result, String scan_result_format) {
        ((TextView)findViewById(R.id.tvFormat)).setText(nombreCompleto);
        final TextView tvResult = (TextView)findViewById(R.id.tvResult);
        tvResult.setText(scan_result);
        Linkify.addLinks(tvResult, Linkify.ALL);
    }
}
