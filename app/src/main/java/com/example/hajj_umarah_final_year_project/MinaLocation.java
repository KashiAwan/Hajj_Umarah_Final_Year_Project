package com.example.hajj_umarah_final_year_project;


import android.app.Notification;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;


/**
 * A simple {@link Fragment} subclass.
 */
public class MinaLocation extends Fragment implements OnMapReadyCallback,  GoogleMap.OnPolygonClickListener {


    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;


    NotificationCompat.Builder mBuilder,mBuilder1;
    PendingIntent pendingIntent,pendingIntent1;
    TaskStackBuilder taskStackBuilder,taskStackBuilder1;
    Intent intent,intent1;
    NotificationManager notificationManager,notificationManager1;


    SupportMapFragment supportMapFragment;

    Polygon polygon;
    GoogleMap map;

    public MinaLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootviewss= inflater.inflate(R.layout.fragment_mina_location, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (supportMapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            supportMapFragment=SupportMapFragment.newInstance();
            ft.replace(R.id.map,supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

        mBuilder = new NotificationCompat.Builder(getActivity());
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Mina Notification");
        mBuilder.setContentText("You are inside Mina don't move away");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mBuilder.setCategory(NotificationCompat.CATEGORY_ALARM);


        intent = new Intent(getActivity(), MinaLocation.class);
        taskStackBuilder = TaskStackBuilder.create(getActivity());
        taskStackBuilder.addParentStack(getActivity());


        taskStackBuilder.addNextIntent(intent);
        pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);


        return rootviewss;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title("Your Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(options);

                //yahan se notification ki he

                mBuilder1 = new NotificationCompat.Builder(getActivity());
                mBuilder1.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder1.setContentTitle("Mina Notification");
                mBuilder1.setContentText("You are Outside Mina Plz get inside");
                mBuilder1.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                mBuilder1.setCategory(NotificationCompat.CATEGORY_ALARM);


                intent1 = new Intent(getActivity(), MinaLocation.class);
                taskStackBuilder1 = TaskStackBuilder.create(getActivity());
                taskStackBuilder1.addParentStack(getActivity());


                taskStackBuilder1.addNextIntent(intent1);
                pendingIntent1 = taskStackBuilder1.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pendingIntent1);

                notificationManager1 = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager1.notify(2, mBuilder1.build());
                //yahan tak he
            }
        });

        // Add a marker in Sydney and move the camera
        //  LatLng sydney = new LatLng(-34, 151);
        //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.

        polygon = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(new LatLng(21.350716, 39.952231),
                        new LatLng(21.380073, 39.977920),
                        new LatLng(21.381315, 39.979920),
                        new LatLng(21.381385, 39.986627),
                        new LatLng(   21.380841 , 39.988071  ),
                        new LatLng(   21.380109 , 39.989009  ),
                        new LatLng(  21.378848  , 39.989648  ),
                        new LatLng(  21.377996  , 39.989899  ),
                        new LatLng(  21.370121  ,  39.989219 ),
                        new LatLng( 21.366620   , 39.987783  ),
                        new LatLng(   21.364604 ,  39.987016 ),
                        new LatLng( 21.363124   , 39.987078  ),
                        new LatLng(   21.361979 , 39.987701  ),
                        new LatLng(21.358079 ,39.993354 ),
                        new LatLng( 21.357083,39.994311 ),
                        new LatLng(21.354963 ,39.995567 ),
                        new LatLng(21.348994 , 39.997062),
                        new LatLng(21.346069 ,39.997435 ),
                        new LatLng( 21.343309, 39.997112),
                        new LatLng( 21.342268,39.996808 ),
                        new LatLng( 21.340778, 39.996111 ),
                        new LatLng(21.338797 , 39.994528),
                        new LatLng( 21.337466,39.993003 ),
                        new LatLng(21.337466 , 39.993003),
                        new LatLng( 21.335387,39.986131 ),
                        new LatLng( 21.333691,39.971528 ),
                        new LatLng( 21.333982, 39.968887 ),
                        new LatLng(21.343583 , 39.952514),
                        new LatLng(21.346728 ,39.950997 ),
                        new LatLng( 21.350716, 39.952231 ))

                .strokeColor(COLOR_PURPLE_ARGB)
                .fillColor(COLOR_GREEN_ARGB));

// Store a data object with the polygon, used here to indicate an arbitrary type.


        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.352356, 39.978423), 13));

        // Set listeners for click events.
        googleMap.setOnPolygonClickListener(this);


    }

    @Override
    public void onPolygonClick(Polygon polygon) {

        notificationManager.notify(1, mBuilder.build());

    }

 /* @Override
    public void onMapClick(LatLng latLng) {

  }*/

      /*  LatLng use =new LatLng(latLng.latitude,latLng.longitude);
        MarkerOptions options = new MarkerOptions()
                .position(use)
                .title("Your Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(options);

        if (use != null){
            Toast.makeText(getActivity(), "found", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();

        }*/

      /*  MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Your Location");
        mMap.addMarker(options);*/

    /*
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());


        mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));*/

    }


