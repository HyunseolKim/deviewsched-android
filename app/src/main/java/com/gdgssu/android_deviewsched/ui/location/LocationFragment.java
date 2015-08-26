package com.gdgssu.android_deviewsched.ui.location;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.ui.MainActivity;
import com.nhn.android.maps.NMapView;

public class LocationFragment extends NMapFragment {

    private static final String KEY_TITLE = "title";
    private CharSequence title;

    private static final String API_KEY = "ac17879e60c5f82514b4255d32a6fa3a";

    public static LocationFragment newInstance(CharSequence title) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.title = getArguments().getString(KEY_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        initToolbar(rootView);
        initNMapView(rootView);

        return rootView;
    }

    private void initNMapView(View rootView) {
        NMapView mMapView = (NMapView)rootView.findViewById(R.id.mapView);

        // initialize map view
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();

        mMapView.setApiKey(API_KEY);
    }


    private void initToolbar(View rootView) {
        Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.fragment_sche_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(this.title);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showHome();
            }
        });
    }
}
