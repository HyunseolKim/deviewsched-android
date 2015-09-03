package com.gdgssu.android_deviewsched.ui.selectsession;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.AllScheItems;

public class SelectSessionActivity extends AppCompatActivity {

    private SelectSessionListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_session);

        initView();

    }

    private void initView() {
        initToolbar();
        initListView();
    }

    private void initListView() {

    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.fragment_sche_toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("세션 선택");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initToolbarSpinner();
    }

    private void initToolbarSpinner() {
        Spinner toolbarSpinner = (Spinner) findViewById(R.id.fragment_sche_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.days, R.layout.toolbar_spinner_item);
        adapter.setDropDownViewResource(R.layout.toolbar_spinner_item_dropdown);
        toolbarSpinner.setAdapter(adapter);
        toolbarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mAdapter.setDayItem(AllScheItems.result.days.get(0));
                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter.setDayItem(AllScheItems.result.days.get(1));
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
