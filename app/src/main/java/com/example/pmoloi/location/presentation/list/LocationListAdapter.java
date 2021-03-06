package com.example.pmoloi.location.presentation.list;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pmoloi.location.R;
import com.example.pmoloi.location.data.model.LocationModel;
import com.example.pmoloi.location.presentation.details.DetailsViewActivity;

import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewLocationName, textViewLocationBestFeature, textViewLocationNumberOfVisits;

        private LocationViewHolder(View textView) {
            super(textView);
            textViewLocationName = textView.findViewById(R.id.textViewListItemLocationName);
            textViewLocationBestFeature = textView.findViewById(R.id.textViewListItemBestFeature);
            textViewLocationNumberOfVisits = textView.findViewById(R.id.textViewListItemNumberOfVisits);
        }
    }

    private final LayoutInflater layoutInflater;
    private List<LocationModel> mLocations;
    private Context context;

    LocationListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new LocationViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        final LocationModel currentLocation = mLocations.get(position);
        holder.textViewLocationName.setText(currentLocation.getLocationName());
        holder.textViewLocationBestFeature.setText(currentLocation.getLocationBestFeature());
        String numberOfVisits = String.valueOf(currentLocation.getLocationNumberOfVisits());
        holder.textViewLocationNumberOfVisits.setText(numberOfVisits);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsViewActivity.class);
                intent.putExtra("LocationId", currentLocation.getLocationId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    void setLocations(List<LocationModel> locations) {
        mLocations = locations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mLocations != null) {
            return mLocations.size();
        } else {
            return 0;
        }
    }
}


