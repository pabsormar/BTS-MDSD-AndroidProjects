package org.bts_netmind.mapmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener,
                                                                    GoogleMap.OnInfoWindowClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    private static final LatLng BTS_LAT_LNG = new LatLng(41.38506, 2.17340);

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment myMapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.fragmentInHostActivity);
            myMapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "onResume()");
    }

    @Override
    public void onMapReady(GoogleMap myGoogleMap)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "onMapReady()");

        // Saving the 'GoogleMap' reference to be used within other methods
        this.myMap = myGoogleMap;

        this.myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.myMap.getUiSettings().setZoomControlsEnabled(true);
        this.myMap.setMyLocationEnabled(true);   // This error/warning has to do with permissions to be asked in the Manifest
       // this.myMap.setTrafficEnabled(true);
       // this.myMap.setIndoorEnabled(true);
       // this.myMap.setBuildingsEnabled(true);
       // this.myMap.getUiSettings().setZoomControlsEnabled(true);
        this.myMap.setOnMapClickListener(this);
        this.myMap.setOnInfoWindowClickListener(this);

        Marker btsMarker = myGoogleMap.addMarker(new MarkerOptions()
                .position(MainActivity.BTS_LAT_LNG)
                .title("BTS")
                .snippet("Learning by doing!"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.bts_icon)))

        // Move the camera instantly to BTS with a zoom of 10.
        this.myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MainActivity.BTS_LAT_LNG, 10));
        // Zoom-in to 14 (out of 21) over the current location within 3 seconds
        // this.myMap.animateCamera(CameraUpdateFactory.zoomTo(14), 3000, null);
    }

    @Override
    public void onMapClick(LatLng latLng)
    {
        Toast.makeText(this, "Map clicked", Toast.LENGTH_SHORT).show();

        this.myMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Title")
                .snippet("Some info to be displayed"));
    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();
    }
}
