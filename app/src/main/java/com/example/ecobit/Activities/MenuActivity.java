package com.example.ecobit.Activities;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.ecobit.Activities.EditarPerfilActivity;
        import com.example.ecobit.Activities.PpalUsuarioActivity;
        import com.example.ecobit.Activities.MisEcobitsActivity;
        import com.example.ecobit.Model.User;
        import com.example.ecobit.R;

        import static com.example.ecobit.R.id.btnSumarCredito;
        import static com.example.ecobit.R.id.tvSumarEcobits;

public class MenuActivity extends AppCompatActivity {

    TextView tvSumarSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final User user = (User) getIntent().getSerializableExtra("usuario");
        setContentView(R.layout.activity_menu);

        String nombre;
        nombre = user.getNombre();
        Toast.makeText(this, "nombre es " + nombre, Toast.LENGTH_SHORT).show();

        tvSumarSaldo = (TextView) findViewById(tvSumarEcobits);
        tvSumarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irSumarEcobits(user);
            }
        });


    }

    //METODO IrPpalUsuario para ir a la página principal del usuario
    public void IrPpalUsuario(View view) {
        Intent irPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
        startActivity(irPpalUsuario);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMiCuenta para ir ver las opciones de la cuenta
    public void IrMiCuenta(View view) {
        Intent irMiCuenta = new Intent(this, MiCuentaActivity.class);
        startActivity(irMiCuenta);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMiPerfil
    public void IrMiPerfil(View view) {
        Intent irMiPerfil = new Intent(this, MiPerfilActivity.class);
        startActivity(irMiPerfil);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMisEcobits
    public void IrMisEcobits(View view) {
        Intent irMisEcobits = new Intent(this, MisEcobitsActivity.class);
        startActivity(irMisEcobits);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrLogros
    public void IrLogros(View view) {
        Intent irLogros = new Intent(this, LogrosActivity.class);
        startActivity(irLogros);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrCanjear para ir a Canjear EcoBits
    public void IrCanjear(View view) {
        Intent irCanjear = new Intent(this, CanjearEcobitsActivity.class);
        startActivity(irCanjear);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO irSumarEcobits-------------------------------------------------------------------------
    public void irSumarEcobits(User _user) {
        final Intent iSumarEcobit = new Intent(this, SumarEcobitsActivity.class);
        iSumarEcobit.putExtra("usuario", _user);
        startActivity(iSumarEcobit);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }



    //METODO IrEventos para ver los eventos y sorteos
    public void IrEventos(View view) {
        Intent irEventos = new Intent(this, EventosActivity.class);
        startActivity(irEventos);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrSobreEcobits
    public void IrSobreEcobits(View view) {
        Intent irSobreEcobits = new Intent(this, SobreEcobitsActivity.class);
        startActivity(irSobreEcobits);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrAyuda
    public void IrAyuda(View view) {
        Intent irAyuda = new Intent(this, AyudaActivity.class);
        startActivity(irAyuda);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}