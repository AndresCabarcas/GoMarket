package com.testing.gomarket;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class tiendaMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "gomarket", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Context mContext = getApplicationContext();

                LinearLayout info = new LinearLayout(mContext);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(mContext);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(mContext);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        LatLng Monteria = new LatLng(8.750296, -75.877758);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.home);
        mMap.addMarker(new MarkerOptions().position(Monteria).title("GoMarket").icon(icon)).setVisible(false);
        for (int i=0; i < helper.getElementos().size();i++){
            LatLng ubicacion = new LatLng(helper.getElementos().get(i).getLatitud(), helper.getElementos().get(i).getLongitud());
            mMap.addMarker(new MarkerOptions().position(ubicacion)
                    .title(helper.getElementos().get(i).getNombre())
                    .snippet(helper.getElementos().get(i).getDescripcion() +System.getProperty ("line.separator")+helper.getElementos().get(i).getHorario())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.home)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Monteria));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(Monteria).zoom(14).bearing(90).tilt(45).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

}