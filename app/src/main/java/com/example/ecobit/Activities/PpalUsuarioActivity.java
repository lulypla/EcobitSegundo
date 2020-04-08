package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;

import com.example.ecobit.utils.CircleTransform;
import com.example.ecobit.utils.JSON;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import static com.example.ecobit.R.id.btnSumarCredito;
import static com.example.ecobit.R.id.imageButtonMenu;







public class PpalUsuarioActivity extends AppCompatActivity {

    ImageView imageViewPerfil;

    Button btnPhoto;
    Button btnSumarSaldo;
    Button btnIrMenu;

    TextView tvNombre;
    TextView tvSaldo;



    //--- Para trasladar usuario por foto-----
    private User usuarioActual;
    String email;
    String pass;
    String nombre;
    String apellido;
    String tipo_doc;
    String nro_documento;
    String fecha_nac;
    String tel;
    String saldo;
    //---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final User user = (User) getIntent().getSerializableExtra("usuario");
        this.usuarioActual = user;
        setContentView(R.layout.activity_ppal_usuario);
        //
        String uriUsuario = usuarioActual.getFoto();
        imageViewPerfil = (ImageView) findViewById(R.id.imageViewPerfil);
        if(uriUsuario != null) {
            Picasso.get().load(user.getFoto()).centerInside().resize(600, 600).transform(new CircleTransform()).into(imageViewPerfil);
        }

        // impresión de datos del perfil
        tvNombre = (TextView) findViewById(R.id.textViewNombre);
        tvNombre.setText(user.getApellido().toString());

        tvSaldo = (TextView) findViewById(R.id.textViewSaldo);
        tvSaldo.setText(user.getSaldo().toString());

        //Toast con saludo----------------------------------------------
        String nombreCompleto;
        nombreCompleto = user.getNombre() + user.getApellido();
        Toast.makeText(PpalUsuarioActivity.this, "Bienvenido " + nombreCompleto, Toast.LENGTH_SHORT).show();

        // botón cargar foto perfil-------------------------------------
        btnPhoto = (Button) findViewById(R.id.btmPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        // botón leer qr------------------------------------------------
        btnSumarSaldo = (Button) findViewById(btnSumarCredito);
        btnSumarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irSumarEcobits(user);
            }
        });

        //boton ir a Menú-----------------------------------------------
        btnIrMenu = (Button) findViewById(R.id.buttonMenu);
        btnIrMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                IrMenu(user);
            }
        } );

        //---
        email = user.getEmail();
        pass = user.getPassword();
        nombre = user.getNombre();
        apellido = user.getApellido();
        tipo_doc = user.getTipo_doc();
        nro_documento = user.getNro_documento();
        fecha_nac = user.getFecha_nac();
        tel = user.getTel();
        saldo = user.getSaldo();
        //---
    }
    private void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
    }
    //METODO IrMenu---------------------------------------------------------------------------------
    public void IrMenu(User usuarioActual) {
        final Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("usuario", usuarioActual);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }


    //METODO irSumarEcobits-------------------------------------------------------------------------
    public void irSumarEcobits(User _user) {
        final Intent iSumarEcobit = new Intent(this, SumarEcobitsActivity.class);
        iSumarEcobit.putExtra("usuario", _user);
        startActivity(iSumarEcobit);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);

    }

    // Captura foto---------------------------------------------------------------------------------
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // RECIBE Y MUESTRA-----------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            fotoPerfil = convertirImgString(imageBitmap);
            subirPhotoPerfil(fotoPerfil);
        }
    }

    //----------------------------------------------------------------------------------------------
    String fotoPerfil;

    private void subirPhotoPerfil(String fotoPerfil) {

        //----------------
        User userUp = new User(email, pass, nombre, apellido, tipo_doc, nro_documento, fecha_nac, tel, fotoPerfil, saldo);
        //----------------
        UserService userService = APIService.getApi().create(UserService.class);
        //JsonObject obj = JSON.bodyActualizarFoto(base64, this.usuarioActual.getEmail());   OTRA FORMA DE PASARLO
        Call<User> userActualizado = userService.postPhoto(userUp);
        final Intent iPpalUsuario = new Intent(this, PpalUsuarioActivity.class);
        userActualizado.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                setUsuarioActual(user);
                if (user != null) {
                    Picasso.get().load(user.getFoto()).centerInside().resize(600, 600).transform(new CircleTransform()).into(imageViewPerfil);
                    Toast.makeText(PpalUsuarioActivity.this, "Foto cargada", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(PpalUsuarioActivity.this, "Foto de Perfil Actualizada", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PpalUsuarioActivity.this, "Error en Pedido, Compruebe su conexión a internet", Toast.LENGTH_LONG).show();
            }
        });

    }

    //----------------------------------------------------------------------------------------------
    String imagenString;

    private String convertirImgString(Bitmap _bitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        _bitmap.compress(Bitmap.CompressFormat.JPEG, 100, array);
        byte[] imagenByte = array.toByteArray();
        imagenString = Base64.encodeToString(imagenByte, Base64.DEFAULT);

        return imagenString;
    }

}





   //LECTURA DE QR----------------------------------------------------------------------------------

   /*
    private View.OnClickListener escanear = new View.OnClickListener() {

       @Override
        public void onClick(View v) {
            switch (v.getId()){
               case R.id.btnSumarCredito:
                    new IntentIntegrator(PpalUsuarioActivity.this).initiateScan();
                    break;
           }
        }
    };
      */

//--------------------------------------------------------------------------------------------------