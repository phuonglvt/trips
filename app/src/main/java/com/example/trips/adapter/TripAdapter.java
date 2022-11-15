package com.example.trips.adapter;
import android.os.Bundle;
import android.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.content.Context;
import java.util.List;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.example.trips.model.Trip;
import com.example.trips.sqlite.TripDatabase;
import com.example.trips.sqlite.DBHelper;
import com.example.trips.model.Trip;

public class TripAdapter extends BaseAdapter{
    private Context context;
    private List<Trip> list;

    public TripAdapter(Context context, List<Trip> list) {
        this.context = context;
        this.list = list;
//        dbHelper = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        View view = LayoutInflater.from(context).inflate(com.example.trips.R.layout.layout_trip_item, null);
        if (view == null){
            view = LayoutInflater.from(context).inflate(com.example.trips.R.layout.layout_trip_item, null);
        }

        TextView tvDestination = view.findViewById(com.example.trips.R.id.tvDestination);
        TextView tvDateOfTheTrip = view.findViewById(com.example.trips.R.id.tvDateOfTheTrip);
        TextView tvRequireAssessment = view.findViewById(com.example.trips.R.id.tvRequireAssessment);
        TextView tvDescription = view.findViewById(com.example.trips.R.id.tvDescription);

        Trip trip = list.get(i);
        tvDestination.setText(trip.getDestination());
        tvDateOfTheTrip.setText(trip.getDateOfTheTrip());
        tvRequireAssessment.setText(""+trip.getRequireAssessment());
        tvDescription.setText(trip.getDescription());

        return view;
    }
}
