package com.example.ecobit.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.example.ecobit.Services.APIService;
import com.example.ecobit.Services.UserService;
import com.example.ecobit.utils.CircleTransform;
import com.example.ecobit.utils.Image;
import com.example.ecobit.utils.JSON;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecobit.R.id.btnSumarCredito;

public class PpalUsuarioActivity extends AppCompatActivity {

    ImageView imageViewPerfil;
    Button btnPhoto;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    TextView tvNombre;
    TextView tvSaldo;
    private User usuarioActual;
    String nombreCompleto;
    // Btn Escaneo
    Button btnSumarSaldo;

    //tvSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User) getIntent().getSerializableExtra("usuario");
        this.usuarioActual = user;
        setContentView(R.layout.activity_ppal_usuario);
        //
        btnPhoto = (Button) findViewById(R.id.btmPhoto);
        String uriUsuario = usuarioActual.getFoto();
        imageViewPerfil = (ImageView) findViewById(R.id.imageViewPerfil);
        if(uriUsuario != null) {
            Picasso.get().load(user.getFoto()).centerInside().resize(600, 600).transform(new CircleTransform()).into(imageViewPerfil);
        }

        // impresión de datos del perfil
        tvNombre = (TextView) findViewById(R.id.textViewNombre);
        tvSaldo =  (TextView)  findViewById(R.id.textViewSaldo);
        tvNombre.setText(user.getNombre());
        String saldo = user.getSaldo();
        if(saldo == null) saldo = "0";
        tvSaldo.setText (saldo);

        //Toast con saludo
        nombreCompleto = user.getApellido();
        Toast.makeText(PpalUsuarioActivity.this, "Bienvenido " + nombreCompleto, Toast.LENGTH_SHORT).show();

        // botón cargar foto perfil
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        // botón leer qr
        btnSumarSaldo = (Button) findViewById(btnSumarCredito);
        btnSumarSaldo.setOnClickListener(escanear);

    }

    private void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
    }

    //METODO IrMenu
    public void IrMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("usuario", this.usuarioActual);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    // Captura foto
    private void dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                    321);

        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String base64 = Image.getBase64(imageBitmap);
            // Liberar la memoria de la imagen
            imageBitmap.recycle();

            UserService userService = APIService.getApi().create(UserService.class);
            // Body de la request
            JsonObject obj = JSON.bodyActualizarFoto(base64, this.usuarioActual.getEmail());
            Call<User> userUpdated = userService.actualizarFoto(obj);
            userUpdated.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    setUsuarioActual(user);
                    // Picasso se encarga de cargar la imagen y transformarla
                    Picasso.get().load(user.getFoto()).centerInside().resize(600, 600).transform(new CircleTransform()).into(imageViewPerfil);

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(PpalUsuarioActivity.this, "Error en Pedido, Compruebe su conexion a internet", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageViewPerfil.getWidth();
        int targetH = imageViewPerfil.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageViewPerfil.setImageBitmap(bitmap);
    }

    //LECTURA DE QR

    private View.OnClickListener escanear = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSumarCredito:
                    new IntentIntegrator(PpalUsuarioActivity.this).initiateScan();
                    break;
            }
        }
    };


}