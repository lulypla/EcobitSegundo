package com.example.ecobit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecobit.Model.User;
import com.example.ecobit.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.Serializable;

import static com.example.ecobit.R.id.btnSumarCredito;

public class PpalUsuarioActivity extends AppCompatActivity {

    ImageView imageViewPerfil;
    Button btnPhoto;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    TextView tvNombre;
    String nombreCompleto;
    // Btn Escaneo
    Button btnSumarSaldo;

    //tvSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User) getIntent().getSerializableExtra("usuario");
        setContentView(R.layout.activity_ppal_usuario);
        //
        btnPhoto=(Button) findViewById(R.id.btmPhoto);
        imageViewPerfil =(ImageView) findViewById(R.id.imageViewPerfil);

        // impresión de datos del perfil
        tvNombre = (TextView)  findViewById(R.id.textViewNombre);
                    //tvSaldo =  (TextView)  findViewById(R.id.textViewSaldo);
       tvNombre.setText (user.getApellido().toString());
        //tvSaldo.setText (user.getPassword().toString());

        //Toast con saludo
        nombreCompleto = user.getApellido();
        Toast.makeText(PpalUsuarioActivity.this, "Bienvenido "+nombreCompleto, Toast.LENGTH_SHORT).show();

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

    //METODO IrMenu
    public void IrMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    // Captura foto
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
         if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewPerfil.setImageBitmap(imageBitmap);
        }
    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageViewPerfil.getWidth();
        int targetH = imageViewPerfil.getHeight(                                     );

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

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
            switch (v.getId()){
               case R.id.btnSumarCredito:
                    new IntentIntegrator(PpalUsuarioActivity.this).initiateScan();
                    break;
           }
        }
    };



}