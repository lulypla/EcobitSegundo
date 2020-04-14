package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ecobit.Activities.MenuActivity;
import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.utils.Sesion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarPerfilActivity extends AppCompatActivity {


    EditText etNombre;
    EditText etApellido;
    EditText etCorreo;
    EditText etCel;
    DatePicker dpFNac;
    RadioButton rbM;
    RadioButton rbF;
    RadioButton rbX;

    private void rellenarCampos() {
        User user = Sesion.getUser(this);
        if (user != null) {
            this.etNombre.setText(user.getNombre());
            this.etApellido.setText(user.getApellido());
            this.etCorreo.setText(user.getEmail());
            etCel.setText(user.getTel());
            Date fnac = getFnac(user.getFecha_nac());
            this.dpFNac.updateDate(fnac.getYear(), fnac.getMonth(), fnac.getDate());
        }


    }

    private Date getFnac(String fec) {
        String DEFAULT_PATTERN = "dd/MM/yyyy";
        Date fechaFinal = new Date();
        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            fechaFinal = formatter.parse(fec);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaFinal;
    }

    private void setCampos() {
        this.etNombre = findViewById(R.id.editTextNombre);
        this.etApellido = findViewById(R.id.editTextApellido);
        this.etCorreo = findViewById(R.id.editTextEmail);
        this.etCel = findViewById(R.id.editTextCelular);
        this.dpFNac = findViewById(R.id.CalendarViewFNac);
        this.rbM = findViewById(R.id.radioM);
        this.rbF = findViewById(R.id.radioF);
        this.rbX = findViewById(R.id.radioX);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        setCampos();
        rellenarCampos();

    }

    public void back(View view) {
        Intent back = new Intent(this, MenuActivity.class);
        startActivity(back);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}
