package com.testing.gomarket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class busquedaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Tienda> tiendasList;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "gomarket", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        //titulo centrado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        tiendasList = new ArrayList<>();

        listarTiendas();

        tiendasAdapter adapter =  new tiendasAdapter(tiendasList);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),
                        "Selecciónado: "+tiendasList.get
                                (recyclerView.getChildAdapterPosition(view))
                                .getNIT(),Toast.LENGTH_SHORT).show();

                String NIT = tiendasList.get(recyclerView.getChildAdapterPosition(view)).getNIT();
                helper.delete(NIT);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void listarTiendas() {
        tiendasList.addAll(helper.getElementos().subList(0,helper.getElementos().size()));
    }


}