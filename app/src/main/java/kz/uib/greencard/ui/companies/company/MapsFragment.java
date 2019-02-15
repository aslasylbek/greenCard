package kz.uib.greencard.ui.companies.company;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import kz.uib.greencard.R;
import kz.uib.greencard.ui.history.HistoryFragment;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapsFragment";
    private GoogleMap mMap;
    private Double latutude, longitude;
    private String companyName;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String COMPANY = "company_name";

    private Boolean mLocationPermissionGranted = false;


    public static MapsFragment newInstance(Double latutude, Double longitude, String companyName) {
        Bundle args = new Bundle();
        MapsFragment fragment = new MapsFragment();
        args.putDouble(LONGITUDE, longitude);
        args.putDouble(LATITUDE, latutude);
        args.putString(COMPANY, companyName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        if(getArguments()!=null){
            latutude = getArguments().getDouble(LATITUDE);
            longitude = getArguments().getDouble(LONGITUDE);
            companyName = getArguments().getString(COMPANY);
            getLocationPermission();
        }
        return view;
    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.e(TAG, "onMapReady: " );
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latutude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title(companyName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));
    }

    private void getLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), COURSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                initMap();
            }else {
                ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else {
            ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length >0){
                    for (int i=0; i<grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    initMap();
                }
            }
        }
    }


}
