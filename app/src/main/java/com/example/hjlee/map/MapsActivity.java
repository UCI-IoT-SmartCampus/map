package com.example.hjlee.map;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements AdapterView.OnItemClickListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public int count=0;
    public int num=0;

    String position;

    static final LatLng universityartgallery = new LatLng(33.649714, -117.844649);
    static final LatLng javacity = new LatLng(33.643434, -117.841239);
    static final LatLng busstop = new LatLng(33.649427, -117.839849);
    static final LatLng foodcourt = new LatLng(33.648788, -117.842271);
    static final LatLng starbucks = new LatLng(33.648260, -117.842082);
    static final LatLng musicmediabldg = new LatLng(33.649269, -117.844609);
    static final LatLng aldrichpark = new LatLng(33.646012, -117.842749);
    static final LatLng frederickreineshall = new LatLng(33.643860, -117.843573);
    static final LatLng aircterrace = new LatLng(33.646161, -117.840576);
    static final LatLng library = new LatLng(33.647261, -117.841328);

    Marker baby_Of_Position_a;
    Marker baby_Of_Position_b;
    Marker baby_Of_Position_c;
    Marker baby_Of_Position_d;
    Marker baby_Of_Position_e;
    Marker a,b,c,d,e,f,g,h,i,j;

    CircleOptions circle = new CircleOptions();


    public String[] arrlist ={
            "second",
            "2",
            "3",
            "4"
    };
    private Circle mCircle;
    private Marker mMarker;

    public Circle mapCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();


       /* ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrlist);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(Adapter);

        list.setOnItemClickListener(this);*/

        a = mMap.addMarker(new MarkerOptions().position(universityartgallery).title("University Art Gallery").alpha(0.7f));
        b = mMap.addMarker(new MarkerOptions().position(javacity).title("Java City").alpha(0.7f));
        c = mMap.addMarker(new MarkerOptions().position(busstop).title("Bus Stop").alpha(0.7f));
        d = mMap.addMarker(new MarkerOptions().position(foodcourt).title("Food Court").alpha(0.7f));
        e = mMap.addMarker(new MarkerOptions().position(starbucks).title("Star Bucks").alpha(0.7f));
        f = mMap.addMarker(new MarkerOptions().position(musicmediabldg).title("Music Medical Bldg").alpha(0.7f));
        g = mMap.addMarker(new MarkerOptions().position(aldrichpark).title("Aldrich Park").alpha(0.7f));
        h = mMap.addMarker(new MarkerOptions().position(frederickreineshall).title("Frederick Reines Hall").alpha(0.7f));
        i = mMap.addMarker(new MarkerOptions().position(aircterrace).title("AIRC Terrace").alpha(0.7f));
        j = mMap.addMarker(new MarkerOptions().position(library).title("Library").alpha(0.7f));



        /*mMap.addCircle(new CircleOptions()
                .center(new LatLng(universityartgallery.latitude, universityartgallery.longitude))
                .radius(100)
                .strokeColor(Color.rgb(238, 213, 210))
                .fillColor(Color.argb(200, 238, 213, 210)));*/

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
                Log.d("arg0", "no point");
                if(baby_Of_Position_a!=null){
                    baby_Of_Position_a.remove();
                    baby_Of_Position_b.remove();
                    baby_Of_Position_c.remove();
                    baby_Of_Position_d.remove();
                    baby_Of_Position_e.remove();

                    if (mapCircle != null) {
                        mapCircle.remove();
                    }
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arg0, 17));
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker arg0) {
                position = arg0.getTitle();

                // Log.d("d", arg0.getTitle());

                LinearLayout bottom_layout = (LinearLayout) findViewById(R.id.bottomLayout);
                TextView myText = (TextView) findViewById(R.id.bottomText);
                myText.setText(arg0.getTitle());
                ImageView image = (ImageView) findViewById(R.id.slidingImage);


                myText.setTextColor(Color.DKGRAY);
                bottom_layout.setBackgroundColor(Color.WHITE);

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arg0.getPosition(), 19));



                circle.strokeColor(Color.rgb(255, 102, 102));
                circle.strokeWidth(2f);
                circle.fillColor(Color.argb(100, 255, 102, 102));
                circle.radius(25);

                if (mapCircle != null) {
                    mapCircle.remove();
                }

               // mapCircle = mMap.addCircle(circle);


                if (arg0.getPosition().equals(universityartgallery)) {
                    image.setImageResource(R.drawable.universityartgallery);



                    return true;
                } else if (arg0.getPosition().equals(javacity)) {
                    image.setImageResource(R.drawable.javacity);

                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    baby_Of_Position_a = mMap.addMarker(new MarkerOptions().position(new LatLng(33.643373, -117.841126)).title("Java City1").alpha(0.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    baby_Of_Position_b = mMap.addMarker(new MarkerOptions().position(new LatLng(33.643462, -117.841268)).title("Java City2").alpha(0.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    baby_Of_Position_c = mMap.addMarker(new MarkerOptions().position(new LatLng(33.643587, -117.841163)).title("Java City3").alpha(0.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    baby_Of_Position_d = mMap.addMarker(new MarkerOptions().position(new LatLng(33.643520, -117.841077)).title("Java City4").alpha(0.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    baby_Of_Position_e = mMap.addMarker(new MarkerOptions().position(new LatLng(33.643413, -117.841080)).title("Java City5").alpha(0.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(busstop)) {
                    image.setImageResource(R.drawable.busstop);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(foodcourt)) {
                    image.setImageResource(R.drawable.foodcourt);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(starbucks)) {
                    image.setImageResource(R.drawable.starbucks);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(musicmediabldg)) {
                    image.setImageResource(R.drawable.musicmediabldg);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(aldrichpark)) {
                    image.setImageResource(R.drawable.aldrichpark);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(frederickreineshall)) {
                    image.setImageResource(R.drawable.frederickreineshall);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(aircterrace)) {
                    image.setImageResource(R.drawable.aircterrace);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else if (arg0.getPosition().equals(library)) {
                    image.setImageResource(R.drawable.library);
                    circle.center(new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude));
                    mapCircle = mMap.addCircle(circle);
                    return true;
                } else {
                    return false;
                }
            }
        });



        final LinearLayout b2 = (LinearLayout) findViewById(R.id.whole);
        final LinearLayout b1 = (LinearLayout) findViewById(R.id.bottomLayout);
        final TextView t1 = (TextView) findViewById(R.id.bottomText);

        b1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        t1.setTextColor(Color.WHITE);
                        b1.setBackgroundResource(R.drawable.translate);
                        TransitionDrawable transition = (TransitionDrawable) b1.getBackground();
                        transition.startTransition(700);
                        break;
                }

                return false;
            }
        });


        ImageButton shadow_graph_Button = (ImageButton) findViewById(R.id.ImageButton);
       // shadow_graph_Button.setBackgroundDrawable(null);
        shadow_graph_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MapsActivity.this, informationActivity.class);
                intent.putExtra("text", position);
                startActivity(intent);

            }
        });

       /* ImageButton power_graph_Button = (ImageButton) findViewById(R.id.ImageButton2);
        power_graph_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, informationActivity.class);
                intent.putExtra("text",position);
                startActivity(intent);

            }
        });*/


    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        String c_list =arrlist[i];
        Intent intent =new Intent(MapsActivity.this, informationActivity.class);
        intent.putExtra("arr_text", c_list);
        startActivity(intent);
    }
}
