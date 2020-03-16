package com.example.ecobit.Activities;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import com.example.ecobit.Activities.EditarPerfilActivity;
        import com.example.ecobit.Activities.PpalUsuarioActivity;
        import com.example.ecobit.Activities.MisEcobitsActivity;
        import com.example.ecobit.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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


}