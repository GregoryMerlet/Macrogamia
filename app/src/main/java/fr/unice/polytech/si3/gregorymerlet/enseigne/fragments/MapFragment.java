package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs.ShopDialog;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Shop;

public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener {

    private static final String FIRM = "firm";
    private Firm firm;
    private MapView mMapView;
    private GoogleMap googleMap;
    private HashMap<Marker, Shop> markers;

    public MapFragment(){
    }

    public static MapFragment newInstance(Firm firm) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);
        this.markers = new HashMap<>();

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setOnMarkerClickListener(MapFragment.this);
                for(Shop shop : firm.getShops()){
                    Marker marker = googleMap.addMarker(new MarkerOptions().position(shop.getLatLng()).title(shop.getName()));
                    markers.put(marker, shop);
                }
                zoomAuto();
            }
        });

        return rootView;
    }

    private void zoomAuto(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers.keySet()) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 300; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
        googleMap.animateCamera(cu);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        ShopDialog shopDialog = new ShopDialog(this.getContext(), markers.get(marker));
        shopDialog.show();
        return false;
    }
}
