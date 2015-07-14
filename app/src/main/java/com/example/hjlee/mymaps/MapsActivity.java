package com.example.hjlee.mymaps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends ActionBarActivity {
    static final LatLng artGallery = new LatLng(33.649714, -117.844649);
    static final LatLng javaCity = new LatLng(33.643434, -117.841239);
    static final LatLng busStop = new LatLng(33.649427, -117.839849);
    static final LatLng foodCoat = new LatLng(33.648788,  -117.842271);
    static final LatLng starBucks = new LatLng(33.648260, -117.842082);
    static final LatLng socialPlaza = new LatLng(33.646751, -117.839257);
    static final LatLng aldrichPark = new LatLng(33.646462, -117.843710);
    static final LatLng FredrickReinesHall = new LatLng(33.643860, -117.843573);
    static final LatLng uct = new LatLng(33.650647, -117.838585);

        private GoogleMap map;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(aldrichPark, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

        Marker a = map.addMarker(new MarkerOptions().position(artGallery).title("artGallery").snippet("showme!"));
        Marker b = map.addMarker(new MarkerOptions().position(javaCity).title("javaCity").snippet("showme!"));
        Marker c = map.addMarker(new MarkerOptions().position(busStop).title("busStop").snippet("showme!"));
        Marker d = map.addMarker(new MarkerOptions().position(foodCoat).title("foodCoat").snippet("showme!"));
        Marker e = map.addMarker(new MarkerOptions().position(starBucks).title("starBucks").snippet("showme!"));
        Marker f = map.addMarker(new MarkerOptions().position(socialPlaza).title("socialPlaza").snippet("showme!"));
        Marker g = map.addMarker(new MarkerOptions().position(aldrichPark).title("aldrichPark").snippet("showme!"));
        Marker h = map.addMarker(new MarkerOptions().position(FredrickReinesHall).title("FredrickReinesHall").snippet("showme!"));
        Marker i = map.addMarker(new MarkerOptions().position(uct).title("uct").snippet("showme!"));



        
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                showAlert(arg0.getTitle());
            }
        });
    }


    public void showAlert (String title) {

            AlertDialog alert2 = new AlertDialog.Builder(MapsActivity.this).setIcon(R.mipmap.ic_launcher).setTitle("aa")
                    .setMessage(title)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

    }




}

