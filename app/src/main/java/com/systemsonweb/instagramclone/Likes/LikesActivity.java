package com.systemsonweb.instagramclone.Likes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.systemsonweb.instagramclone.Home.CameraFragment;
import com.systemsonweb.instagramclone.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.systemsonweb.instagramclone.R;
import com.systemsonweb.instagramclone.Utils.BottomNavigationViewHelper;


public class LikesActivity extends FragmentActivity implements OnMapReadyCallback{
    private static final String TAG = "LikesActivity";
    private static final int ACTIVITY_NUM = 3;
    private FusedLocationProviderClient client;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private static final int REQuEST_CODE = 101;

    //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    private Button button;
    private TextView textView;
    private LocationManager mlocationManager;
    private LocationListener mlocationListener;
    //private Context mContext;

    private MapView mapView;
    private GoogleMap googleMap;

    private Context mContext = LikesActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likes_tab);

        String imageFromDrawable = "@drawable/dsulogot";
        ImageView imageView = (ImageView) findViewById(R.id.imageMap);
        // Inflate the layout for this fragment
        //ImageView.setImageResource(AndroidImageAssete.getmeads.get(0));
        imageView.setImageResource(R.drawable.mmap);

        //requestPermission();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(LikesActivity.this);

        //client = LocationServices.getFusedLocationProviderClient(CameraFragment.this.getActivity());

        button = (Button) findViewById(R.id.cordButton);
        textView = (TextView) findViewById(R.id.text_view_id);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(LikesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LikesActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LikesActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION}, REQuEST_CODE);
                    return;
                }

                Task<Location> task = fusedLocationProviderClient.getLastLocation();
                task.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            currentLocation = location;
                            Toast.makeText(getApplicationContext(), currentLocation.getLatitude() +
                                    "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                            SupportMapFragment supportMapFragment = (SupportMapFragment) LikesActivity.this.getSupportFragmentManager().findFragmentById(R.id.google_map);
                            //SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.mapFragment);
                            //supportMapFragment.getMapAsync(CameraFragment.this);
                            TextView textView = findViewById(R.id.text_view_id);
                            textView.setText(location.toString());
                            if (supportMapFragment != null) {
                                supportMapFragment.getMapAsync(LikesActivity.this);
                            }
                        }
                    }
                });
            };
        });


        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        //fetchLastLocation();


    }


    public void fetchLastLocation(){
        if (ActivityCompat.checkSelfPermission(LikesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LikesActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LikesActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQuEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() +
                            "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) LikesActivity.this.getSupportFragmentManager().findFragmentById(R.id.google_map);
                    //SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.mapFragment);
                    //supportMapFragment.getMapAsync(CameraFragment.this);
                    if (supportMapFragment != null) {
                        supportMapFragment.getMapAsync(LikesActivity.this);
                    }
                }
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(LikesActivity.this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){

        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOption = new MarkerOptions().position(latLng).title("You Are Here");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        googleMap.addMarker(markerOption);

        //massin.peters
        //charles.m
        //aacohen
        //rodrigo.lapenne


    }





    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}