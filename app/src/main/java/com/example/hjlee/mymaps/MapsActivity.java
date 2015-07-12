package com.example.hjlee.mymaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends ActionBarActivity {
    static final LatLng UCI = new LatLng(33.64, -117.84);
    static final LatLng point1 = new LatLng(33.645443, -117.843140);
   /* static final LatLng point2 = new LatLng(33.64, -117.84);
    static final LatLng point3 = new LatLng(33.64, -117.84);
    static final LatLng point4 = new LatLng(33.64, -117.84);
    static final LatLng point5 = new LatLng(33.64, -117.84);
    static final LatLng point6 = new LatLng(33.64, -117.84);
    static final LatLng point7 = new LatLng(33.64, -117.84);
    static final LatLng point8 = new LatLng(33.64, -117.84);
    static final LatLng point9 = new LatLng(33.64, -117.84);
    static final LatLng         point10 = new LatLng(33.64, -117.84);*/

        private GoogleMap map;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        Marker a = map.addMarker(new MarkerOptions().position(UCI).title("hi"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(UCI, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

}

