package com.example.pmoloi.location.adapter;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.pmoloi.location.R;
import com.example.pmoloi.location.model.LocationModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;

public class MapMarkersAdapter extends ArrayAdapter<LocationModel>
{
    private final LayoutInflater layoutInflater;
    private final HashSet<MapView> mMaps = new HashSet<MapView>();

    public MapMarkersAdapter(Context context,int resource)
    {
        super(context,resource);
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View mapRow = convertView;
        ViewHolder holder;

        if(mapRow == null)
        {
            mapRow = layoutInflater.inflate(R.layout.activity_maps,null);
            holder = new ViewHolder();
            mapRow.setTag(holder);
            holder.initializeMap();

            mMaps.add(holder.mapView);
        }
        else
        {
            holder =(ViewHolder) mapRow.getTag();
        }
        LocationModel mapPoint = getItem(position);
        holder.mapView.setTag(mapPoint);

        if (holder.map != null)
        {
            setMapMarkers(holder.map, mapPoint);
        }
        return mapRow;
    }

    public HashSet<MapView> getMaps()
    {
        return  mMaps;
    }
    public void setMapMarkers(GoogleMap map, LocationModel locationModel)
    {
        map.addMarker(new MarkerOptions().position(new LatLng(locationModel.getLocationLatitude(),locationModel.getLocationLongitude()))
                .title(locationModel.getLocationName()));
    }
    class ViewHolder implements OnMapReadyCallback
    {
        MapView mapView;
        GoogleMap map;

        public void initializeMap()
    {
        if(mapView != null)
        {
            mapView.onCreate(null);
            mapView.getMapAsync(this);
        }
    }

    @Override
        public void onMapReady(GoogleMap googleMap)
    {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        LocationModel locationModel = (LocationModel) mapView.getTag();
        if(locationModel != null)
        {
            setMapMarkers(map,locationModel);
        }
    }
    }

}