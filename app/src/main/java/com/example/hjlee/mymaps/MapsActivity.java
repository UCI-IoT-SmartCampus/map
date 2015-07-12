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
 static final LatLng SEOUL = new LatLng( 37.56, 126.97);
    private GoogleMap map;
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
   map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
 .getMap();
Marker seoul = map.addMarker(new MarkerOptions().position(SEOUL)
.title("Seoul"));
map.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
}
/*public boolean onCreateOptionsMenu(Menu menu) {

getMenuInflater().inflate(R.menu.menu_main, menu);
return true;
}
 public boolean onOptionsItemSelected(MenuItem item) {
 int id = item.getItemId();
  if (id == R.id.action_settings) {

 return true;

}
 return super.onOptionsItemSelected(item);
  }*/
}

