package com.systemsonweb.instagramclone.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.DefaultCompany.arMapCap.UnityPlayerActivity;
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
import com.systemsonweb.instagramclone.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";
    private FusedLocationProviderClient client;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private static final int REQuEST_CODE = 101;

    //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    private Button button;
    private TextView textView;
    private LocationManager mlocationManager;
    private LocationListener mlocationListener;
    private Context mContext;

    private MapView mapView;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        String imageFromDrawable = "@drawable/dsulogot";
        ImageView imageView = (ImageView) view.findViewById(R.id.imageMap);
        // Inflate the layout for this fragment
        //ImageView.setImageResource(AndroidImageAssete.getmeads.get(0));
        imageView.setImageResource(R.drawable.mmap);

        button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CameraFragment.this.getActivity(), UnityPlayerActivity.class);
                startActivity(i);


            }
        });
        //requestPermission();
        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(CameraFragment.this.getActivity());
        //fetchLastLocation();

        //client = LocationServices.getFusedLocationProviderClient(CameraFragment.this.getActivity());

        //button = (Button) view.findViewById(R.id.cordButton);
        //textView = (TextView) view.findViewById(R.id.text_view_id);


        return view;
    }





}