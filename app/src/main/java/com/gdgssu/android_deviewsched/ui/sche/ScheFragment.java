package com.gdgssu.android_deviewsched.ui.sche;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.AllScheItems;
import com.gdgssu.android_deviewsched.ui.DeviewFragment;
import com.gdgssu.android_deviewsched.ui.MainActivity;

public class ScheFragment extends DeviewFragment {

    private static final String KEY_TITLE = "title";

    private ViewPager mPager;
    private CharSequence title;

    private String TAG = "ScheFragment";

    private SchePagerFragmentAdapter mAdapter;

    public static ScheFragment newInstance(CharSequence title) {
        ScheFragment fragment = new ScheFragment();
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public ScheFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_sche, container, false);

        initToolbar(rootView);
        initFragmentPager(rootView);
        initTabLayout(rootView);

        return rootView;
    }

    private void initTabLayout(View rootView) {
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.fragment_sche_tabs);
        tabLayout.setupWithViewPager(mPager);
    }

    private void initFragmentPager(View rootView) {
        mPager = (ViewPager) rootView.findViewById(R.id.fragment_sche_content_pager);
        mAdapter = new SchePagerFragmentAdapter(getChildFragmentManager(), AllScheItems.result.days.get(0));

        mPager.setAdapter(mAdapter);
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

        initToolbarSpinner(rootView);
    }

    private void initToolbarSpinner(View rootView) {
        Spinner toolbarSpinner = (Spinner) rootView.findViewById(R.id.fragment_sche_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.days, R.layout.toolbar_spinner_item);
        adapter.setDropDownViewResource(R.layout.toolbar_spinner_item_dropdown);
        toolbarSpinner.setAdapter(adapter);
        toolbarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    mAdapter.setDayItem(AllScheItems.result.days.get(0));
                    mAdapter.notifyDataSetChanged();
                    Log.d(TAG, "day1");
                }else{
                    mAdapter.setDayItem(AllScheItems.result.days.get(1));
                    mAdapter.notifyDataSetChanged();
                    Log.d(TAG, AllScheItems.result.days.get(1).tracks.get(0).sessions.get(0).title);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
