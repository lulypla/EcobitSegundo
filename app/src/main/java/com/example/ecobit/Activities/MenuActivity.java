package com.example.ecobit.Activities;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import com.example.ecobit.Activities.EditarPerfilActivity;
        import com.example.ecobit.Activities.PpalUsuarioActivity;
        import com.example.ecobit.Activities.MisEcobitsActivity;
        import com.example.ecobit.Model.User;
        import com.example.ecobit.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    private void irConUsuario(Intent intent) {
        User user = (User) getIntent().getSerializableExtra("usuario");
        intent.putExtra("usuario", user);
         startActivity(intent);
    }

    //METODO IrPpalUsuario para ir a la página principal del usuario
    public void IrPpalUsuario(View view) {
        Intent irPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
        irConUsuario(irPpalUsuario);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMiCuenta para ir ver las opciones de la cuenta
    public void IrMiCuenta(View view) {
        Intent irMiCuenta = new Intent(this, MiCuentaActivity.class);
        irConUsuario(irMiCuenta);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMiPerfil
    public void IrMiPerfil(View view) {
        Intent irMiPerfil = new Intent(this, MiPerfilActivity.class);
        irConUsuario(irMiPerfil);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMisEcobits
    public void IrMisEcobits(View view) {
        Intent irMisEcobits = new Intent(this, MisEcobitsActivity.class);
        irConUsuario(irMisEcobits);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrLogros
    public void IrLogros(View view) {
        Intent irLogros = new Intent(this, LogrosActivity.class);
        irConUsuario(irLogros);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrCanjear para ir a Canjear EcoBits
    public void IrCanjear(View view) {
        Intent irCanjear = new Intent(this, CanjearEcobitsActivity.class);
        irConUsuario(irCanjear);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrEventos para ver los eventos y sorteos
    public void IrEventos(View view) {
        Intent irEventos = new Intent(this, EventosActivity.class);
        irConUsuario(irEventos);
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
        irConUsuario(irAyuda);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}