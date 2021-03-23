package com.testing.gomarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.skydoves.elasticviews.ElasticCheckButton;

public class adminActivity extends AppCompatActivity {

    ElasticCheckButton consultarLatLong, BtnGuardar;
    EditText edtLat, edtLong, edtDir, edtDescripcion, edtNit, edtNombre, edtHorario;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "gomarket", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //titulo centrado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        consultarLatLong = (ElasticCheckButton) findViewById(R.id.botton);
        BtnGuardar = (ElasticCheckButton) findViewById(R.id.btnEnviar);
        edtLat = findViewById(R.id.txtLat);
        edtLong = findViewById(R.id.txtLong);
        edtDir = findViewById(R.id.txtdir);
        edtDescripcion = findViewById(R.id.txtdescripcion);
        edtNit = findViewById(R.id.txtnit);
        edtNombre = findViewById(R.id.txtNombre);
        edtHorario = findViewById(R.id.txthorario);

        getLocalizacion();
        CargarLocalizacion();
        guardarDatos();
    }

    private void guardarDatos() {
        BtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                String latitud = edtLat.getText().toString().trim();
                String longitud = edtLong.getText().toString().trim();
                String direccion = edtDir.getText().toString().trim();
                String descripcion = edtDescripcion.getText().toString().trim();
                String nit = edtNit.getText().toString().trim();
                String nombre = edtNombre.getText().toString().trim();
                String img = "null";
                String horario = edtHorario.getText().toString().trim();

                if (TextUtils.isEmpty(latitud)){
                    Toast.makeText(getApplicationContext(), "Por favor Ingrese o genere la Latitud", Toast.LENGTH_SHORT).show();
                }else{
                    if (TextUtils.isEmpty(longitud)){
                        Toast.makeText(getApplicationContext(), "Por favor Ingrese o genere la Longitud", Toast.LENGTH_SHORT).show();
                    }else{
                        if (TextUtils.isEmpty(direccion)){
                            Toast.makeText(getApplicationContext(), "Por favor Ingrese la direccion", Toast.LENGTH_SHORT).show();
                        }else{
                            if (TextUtils.isEmpty(descripcion)){
                                Toast.makeText(getApplicationContext(), "Por favor Ingrese la descripcion", Toast.LENGTH_SHORT).show();
                            }else{
                                if (TextUtils.isEmpty(nit)){
                                    Toast.makeText(getApplicationContext(), "Por favor Ingrese el nit", Toast.LENGTH_SHORT).show();
                                }else{
                                    if (TextUtils.isEmpty(nombre)){
                                        Toast.makeText(getApplicationContext(), "Por favor Ingrese el nombre", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
                  helper.insertar(nombre,nit,direccion,descripcion,img,latitud,longitud,horario);
                  Toast.makeText(getApplicationContext(), "Datos Guardados con exito", Toast.LENGTH_SHORT).show();
                  helper.cerrar();
                Intent intent = new Intent(adminActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void CargarLocalizacion() {
        consultarLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) adminActivity.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        edtLat.setText(""+location.getLatitude());
                        edtLong.setText(""+location.getLongitude());
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                int permiso = ContextCompat.checkSelfPermission(adminActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Toast.makeText(getApplicationContext(), "Ubicacion generada Con exito",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getLocalizacion() {

    }
}