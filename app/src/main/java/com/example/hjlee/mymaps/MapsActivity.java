package com.example.hjlee.mymaps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.TimeUnit;

public class MapsActivity extends ActionBarActivity {
    static final LatLng artGallery = new LatLng(33.649714, -117.844649);
    static final LatLng javaCity = new LatLng(33.643434, -117.841239);
    static final LatLng busStop = new LatLng(33.649427, -117.839849);
    static final LatLng foodCoat = new LatLng(33.648788, -117.842271);
    static final LatLng starBucks = new LatLng(33.648260, -117.842082);
    static final LatLng socialPlaza = new LatLng(33.646751, -117.839257);
    static final LatLng aldrichPark = new LatLng(33.646462, -117.843710);
    static final LatLng FredrickReinesHall = new LatLng(33.643860, -117.843573);
    static final LatLng uct = new LatLng(33.650647, -117.838585);

    private GoogleMap map;
    String arrlist[] = {"solar energy", "shading graph", "weather", "solar loss rate"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(aldrichPark, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

        Marker a = map.addMarker(new MarkerOptions().position(artGallery).title("artGallery"));
        Marker b = map.addMarker(new MarkerOptions().position(javaCity).title("javaCity"));
        Marker c = map.addMarker(new MarkerOptions().position(busStop).title("busStop"));
        Marker d = map.addMarker(new MarkerOptions().position(foodCoat).title("foodCoat"));
        Marker e = map.addMarker(new MarkerOptions().position(starBucks).title("starBucks"));
        Marker f = map.addMarker(new MarkerOptions().position(socialPlaza).title("socialPlaza"));
        Marker g = map.addMarker(new MarkerOptions().position(aldrichPark).title("aldrichPark"));
        Marker h = map.addMarker(new MarkerOptions().position(FredrickReinesHall).title("FredrickReinesHall"));
        Marker i = map.addMarker(new MarkerOptions().position(uct).title("uct"));


        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker arg0) {
               // map.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
                showAlert(arg0.getTitle());
            }
        });
    }


    public void showAlert(String title) {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapsActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.custom, null);

        alertDialog.setView(convertView);
        alertDialog.setTitle(title);

        ListView list = (ListView) convertView.findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrlist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String c_list = arrlist[position];
                Intent intent = new Intent(MapsActivity.this, informationActivity.class);
                intent.putExtra("arr_text", c_list);
                startActivity(intent);
            }
        });

        alertDialog.show();

    }

}

