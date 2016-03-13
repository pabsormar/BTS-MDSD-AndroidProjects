package org.bts_netmind.mapmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    private static final LatLng BTS_LAT_LNG = new LatLng(41.38506, 2.17340);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment myMap = ((MapFragment) this.getFragmentManager().findFragmentById(R.id.fragmentInHostActivity));
        myMap.getMapAsync(this);
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

        myGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        myGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        myGoogleMap.setMyLocationEnabled(true);   // This error/warning has to do with permissions to be asked in the Manifest
       // myGoogleMap.setTrafficEnabled(true);
       // myGoogleMap.setIndoorEnabled(true);
       // myGoogleMap.setBuildingsEnabled(true);
       // myGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        Marker btsMarker = myGoogleMap.addMarker(new MarkerOptions()
                .position(MainActivity.BTS_LAT_LNG)
                .title("BTS")
                .snippet("Learning by doing!"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.bts_icon)))

        // Move the camera instantly to BTS with a zoom of 10.
        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MainActivity.BTS_LAT_LNG, 10));
        // Zoom-in to 14 (out of 21) over the current location within 3 seconds
        // myGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14), 3000, null);
    }
}
