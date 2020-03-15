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

    //METODO IrEditarPerfil
    public void IrEditarPerfil(View view) {
        Intent irEditarPerfil = new Intent(this, EditarPerfilActivity.class);
        startActivity(irEditarPerfil);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    //METODO IrMisEcobits
    public void IrMisEcobits(View view) {
        Intent irMisEcobits = new Intent(this, MisEcobitsActivity.class);
        startActivity(irMisEcobits);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}