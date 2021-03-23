package com.testing.gomarket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MenuActivity extends AppCompatActivity {

    CardView admin,tiendas,busqueda,ayuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //titulo centrado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
    //Casteando variables
        admin  = (CardView)findViewById(R.id.idcard1);
        tiendas  = (CardView)findViewById(R.id.idcard2);
        busqueda  = (CardView)findViewById(R.id.idcard3);
        ayuda  = (CardView)findViewById(R.id.idcard4);
    //llamando los metodos
        getAdmin();
        getTiendas();
        getBusqueda();
        getAyuda();
    }

    private void getAyuda() {
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ayudaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getBusqueda() {
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, busquedaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getTiendas() {
        tiendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, tiendaMapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAdmin() {
    admin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuActivity.this, adminActivity.class);
            startActivity(intent);
        }
    });
    }
}